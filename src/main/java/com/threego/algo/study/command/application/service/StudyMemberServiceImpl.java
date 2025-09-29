package com.threego.algo.study.command.application.service;

import com.threego.algo.member.command.domain.aggregate.Member;
import com.threego.algo.member.command.domain.repository.MemberRepository;
import com.threego.algo.study.command.domain.aggregate.Study;
import com.threego.algo.study.command.domain.aggregate.StudyMember;
import com.threego.algo.study.command.domain.aggregate.enums.StudyRole;
import com.threego.algo.study.command.domain.repository.StudyMemberRepository;
import com.threego.algo.study.command.domain.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StudyMemberServiceImpl implements StudyMemberService {
    private final StudyRepository studyRepository;
    private final MemberRepository memberRepository;
    private final StudyMemberRepository studyMemberRepository;

    @Override
    public ResponseEntity<String> kickMember(int studyId, int memberId, int leaderId) {
        try {
            Study study = studyRepository.findById(studyId)
                    .orElseThrow(() -> new IllegalArgumentException("스터디 그룹을 찾을 수 없습니다."));

            Member leader = memberRepository.findById(leaderId)
                    .orElseThrow(() -> new IllegalArgumentException("그룹장을 찾을 수 없습니다."));

            Member memberToKick = memberRepository.findById(memberId)
                    .orElseThrow(() -> new IllegalArgumentException("강퇴할 멤버를 찾을 수 없습니다."));

            // 현재 사용자가 그룹장인지 확인
            StudyMember leaderMember = (StudyMember) studyMemberRepository.findByStudyAndMember(study, leader)
                    .orElseThrow(() -> new IllegalArgumentException("스터디 멤버를 찾을 수 없습니다."));

            if (leaderMember.getRole() != StudyRole.LEADER) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("그룹장만 멤버를 강퇴할 수 있습니다.");
            }

            // 그룹장 자신을 강퇴하려는 경우 방지
            if (leader.getId()!=(memberId)) {
                return ResponseEntity.badRequest().body("그룹장은 자신을 강퇴할 수 없습니다.");
            }

            StudyMember studyMemberToKick = (StudyMember) studyMemberRepository.findByStudyAndMember(study, memberToKick)
                    .orElseThrow(() -> new IllegalArgumentException("해당 사용자는 그룹 멤버가 아닙니다."));

            // 소프트 딜리트: NOT_MEMBER로 상태만 변경
            studyMemberToKick.kickOut();
            studyMemberRepository.save(studyMemberToKick);

            return ResponseEntity.ok("그룹원을 강퇴했습니다.");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("그룹원 강퇴 중 오류가 발생했습니다.");
        }
    }

    @Override
    public ResponseEntity<String> delegateLeadership(int studyId, int newLeaderId, int currentLeaderId) {
        try {
            Study study = studyRepository.findById(studyId)
                    .orElseThrow(() -> new IllegalArgumentException("스터디 그룹을 찾을 수 없습니다."));

            Member currentLeader = memberRepository.findById(currentLeaderId)
                    .orElseThrow(() -> new IllegalArgumentException("현재 그룹장을 찾을 수 없습니다."));

            Member newLeader = memberRepository.findById(newLeaderId)
                    .orElseThrow(() -> new IllegalArgumentException("새로운 그룹장을 찾을 수 없습니다."));

            // 현재 사용자가 그룹장인지 확인
            StudyMember currentLeaderMember = (StudyMember) studyMemberRepository.findByStudyAndMember(study, currentLeader)
                    .orElseThrow(() -> new IllegalArgumentException("스터디 멤버를 찾을 수 없습니다."));

            if (currentLeaderMember.getRole() != StudyRole.LEADER) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("그룹장만 권한을 위임할 수 있습니다.");
            }

            // 새로운 그룹장이 그룹 멤버인지 확인
            StudyMember newLeaderMember = (StudyMember) studyMemberRepository.findByStudyAndMember(study, newLeader)
                    .orElseThrow(() -> new IllegalArgumentException("권한을 위임받을 멤버가 그룹에 없습니다."));

            // 권한 변경
            currentLeaderMember.changeRole(StudyRole.MEMBER);
            newLeaderMember.changeRole(StudyRole.LEADER);

            studyMemberRepository.save(currentLeaderMember);
            studyMemberRepository.save(newLeaderMember);

            return ResponseEntity.ok("그룹장 권한을 위임했습니다.");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("권한 위임 중 오류가 발생했습니다.");
        }
    }
}
