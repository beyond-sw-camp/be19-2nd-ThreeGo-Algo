package com.threego.algo.study.command.application.service;

import com.threego.algo.member.command.domain.aggregate.Member;
import com.threego.algo.member.command.domain.repository.MemberRepository;
import com.threego.algo.study.command.application.dto.create.StudyCreateDTO;
import com.threego.algo.study.command.application.dto.update.StudyUpdateDTO;
import com.threego.algo.study.command.domain.aggregate.Study;
import com.threego.algo.study.command.domain.aggregate.StudyMember;
import com.threego.algo.study.command.domain.aggregate.enums.StudyRole;
import com.threego.algo.study.command.domain.repository.StudyMemberRepository;
import com.threego.algo.study.command.domain.repository.StudyRepository;
import com.threego.algo.studyrecruit.command.application.service.StudyRecruitPostService;
import com.threego.algo.studyrecruit.command.domain.aggregate.StudyRecruitPost;
import com.threego.algo.studyrecruit.command.domain.aggregate.enums.RecruitStatus;
import com.threego.algo.studyrecruit.command.domain.repository.StudyRecruitPostRepository;
import com.threego.algo.studyrecruit.query.dto.StudyRecruitMemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StudyServiceImpl implements StudyService {

    private final MemberRepository memberRepository;
    private final StudyRepository studyRepository;
    private final StudyMemberRepository studyMemberRepository;
    private final StudyRecruitPostRepository studyRecruitPostRepository;
    private final StudyRecruitPostService studyRecruitPostService;

    /* 설명. 스터디 생성 */
    @Override
    public ResponseEntity<String> createStudyFromRecruit(int authorId, int postId, StudyCreateDTO request) {
        try {
            // 1. 모집글 존재 여부 및 권한 확인
            StudyRecruitPost recruitPost = studyRecruitPostRepository
                    .findByIdAndMemberIdAndVisibility(postId, authorId, "Y")  // request.getRecruitPostId() → postId
                    .orElseThrow(() -> new IllegalArgumentException("권한이 없거나 존재하지 않는 모집글입니다."));

            // 2. 모집글 상태 확인 (CLOSED 상태여야 함)
            if (recruitPost.getStatus() != RecruitStatus.CLOSED) {
                return ResponseEntity.badRequest().body("모집이 마감되지 않은 게시물입니다.");
            }

            // 3. 이미 스터디가 생성되었는지 확인
            if (studyRepository.existsByRecruitPostId(postId)) {  // request.getRecruitPostId() → postId
                return ResponseEntity.badRequest().body("이미 스터디가 생성된 모집글입니다.");
            }

            // 4. MyBatis로 참가 신청자 목록 조회 후 승인된 멤버 필터링
            List<StudyRecruitMemberDTO> allApplicants = studyRecruitPostService.findStudyRecruitMembers(postId);  // request.getRecruitPostId() → postId
            List<StudyRecruitMemberDTO> approvedApplicants = allApplicants.stream()
                    .filter(applicant -> "APPROVED".equals(applicant.getStatus()))
                    .collect(Collectors.toList());

            // 5. 스터디 그룹 생성
            Study study = new Study(
                    postId,  // request.getRecruitPostId() → postId
                    request.getName(),
                    request.getDescription(),
                    request.getStartDate(),
                    request.getEndDate()
            );
            Study savedStudy = studyRepository.save(study);

            // 6. 스터디 멤버 추가 (작성자를 리더로)
            StudyMember leader = new StudyMember(savedStudy, recruitPost.getMember(), StudyRole.LEADER);
            studyMemberRepository.save(leader);

            // 7. 승인된 신청자들을 멤버로 추가
            for (StudyRecruitMemberDTO approvedApplicant : approvedApplicants) {
                // Member 엔티티 조회 (간단한 조회이므로 JPA 사용)
                Member member = memberRepository.findById(Math.toIntExact(approvedApplicant.getMemberId()))
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다: " + approvedApplicant.getMemberId()));

                StudyMember studyMember = new StudyMember(savedStudy, member, StudyRole.MEMBER);
                studyMemberRepository.save(studyMember);
            }

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("스터디 그룹이 성공적으로 생성되었습니다. (멤버 " + (approvedApplicants.size() + 1) + "명)");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("스터디 생성 중 오류가 발생했습니다.");
        }
    }

    /* 설명. 스터디 정보 수정 */
    @Override
    public ResponseEntity<String> updateStudy(int studyId, int leaderId, StudyUpdateDTO request) {
        try {
            // 1. 스터디 존재 여부 확인
            Study study = studyRepository.findById(studyId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스터디입니다."));

            // 2. 리더 권한 확인
            boolean isLeader = studyMemberRepository.existsByStudyIdAndMemberIdAndRole(
                    studyId, leaderId, StudyRole.LEADER);

            if (!isLeader) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("스터디 정보를 수정할 권한이 없습니다. (그룹장만 가능)");
            }

            // 3. 스터디 정보 업데이트
            study.updateStudy(request.getName(), request.getDescription(), request.getEndDate());

            // 4. 저장 (더티 체킹으로 자동 업데이트)
            studyRepository.save(study);

            return ResponseEntity.ok("스터디 정보가 성공적으로 수정되었습니다.");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("스터디 정보 수정 중 오류가 발생했습니다.");
        }
    }

    /* 설명. 스터디 그룹 삭제 */
    @Override
    public ResponseEntity<String> deleteStudy(int studyId, int leaderId) {
        try {
            Study study = studyRepository.findById(studyId)
                    .orElseThrow(() -> new IllegalArgumentException("스터디 그룹을 찾을 수 없습니다."));

            Member leader = memberRepository.findById(leaderId)
                    .orElseThrow(() -> new IllegalArgumentException("그룹장을 찾을 수 없습니다."));

            // 현재 사용자가 그룹장인지 확인
            StudyMember leaderMember = (StudyMember) studyMemberRepository.findByStudyAndMember(study, leader)
                    .orElseThrow(() -> new IllegalArgumentException("스터디 멤버를 찾을 수 없습니다."));

            if (leaderMember.getRole() != StudyRole.LEADER) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("그룹장만 그룹을 삭제할 수 있습니다.");
            }

            // 다른 그룹원이 있는지 확인 (그룹장 제외)
            long memberCount = studyMemberRepository.countByStudyAndMemberNot(study, leader);
            if (memberCount > 0) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("그룹원이 있는 상태에서는 그룹을 삭제할 수 없습니다.");
            }

            // 그룹장도 멤버 테이블에서 제거
            studyMemberRepository.delete(leaderMember);

            // 스터디 그룹 삭제
            studyRepository.delete(study);

            return ResponseEntity.ok("스터디 그룹을 삭제했습니다.");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("스터디 그룹 삭제 중 오류가 발생했습니다.");
        }
    }
}