package com.threego.algo.studyrecruit.command.application.service;

import com.threego.algo.common.util.DateTimeUtils;
import com.threego.algo.member.command.domain.aggregate.Member;
import com.threego.algo.member.command.domain.repository.MemberRepository;
import com.threego.algo.studyrecruit.command.application.dto.create.StudyRecruitReportCreateDTO;
import com.threego.algo.studyrecruit.command.domain.aggregate.StudyRecruitComment;
import com.threego.algo.studyrecruit.command.domain.aggregate.StudyRecruitPost;
import com.threego.algo.studyrecruit.command.domain.aggregate.StudyRecruitReport;
import com.threego.algo.studyrecruit.command.domain.repository.StudyRecruitCommentRepository;
import com.threego.algo.studyrecruit.command.domain.repository.StudyRecruitPostRepository;
import com.threego.algo.studyrecruit.command.domain.repository.StudyRecruitReportRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class StudyRecruitReportServiceImpl implements StudyRecruitReportService {

    private final StudyRecruitReportRepository studyRecruitReportRepository;
    private final StudyRecruitPostRepository studyRecruitPostRepository;
    private final StudyRecruitCommentRepository studyRecruitCommentRepository;
    private final MemberRepository memberRepository;

    @Override
    public ResponseEntity<String> reportPost(Integer postId, Integer reporterId, StudyRecruitReportCreateDTO request) {
        try {
            // 1. 신고자 존재 여부 확인
            Member reporter = memberRepository.findById(reporterId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

            // 2. 신고할 모집글 존재 여부 확인 (공개상태만)
            StudyRecruitPost studyRecruitPost = studyRecruitPostRepository
                    .findById(postId)
                    .filter(post -> post.getVisibility() == "Y")
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않거나 공개되지 않은 모집글입니다."));

            // 3. 중복 신고 확인
            if (studyRecruitReportRepository.existsByReporterIdAndStudyRecruitPostId(reporterId, postId)) {
                return ResponseEntity.badRequest().body("이미 신고한 모집글입니다.");
            }

            // 4. 자기 자신의 글 신고 방지
            if (studyRecruitPost.getMember().getId().equals(reporterId)) {
                return ResponseEntity.badRequest().body("본인이 작성한 모집글은 신고할 수 없습니다.");
            }

            // 5. 신고 Entity 생성 및 저장
            StudyRecruitReport report = new StudyRecruitReport(
                    reporter,
                    studyRecruitPost,
                    request.getReason(),
                    DateTimeUtils.nowDateTime()
            );

            studyRecruitReportRepository.save(report);

            return ResponseEntity.status(HttpStatus.CREATED).body("모집글 신고가 완료되었습니다.");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("모집글 신고 중 오류가 발생했습니다.");
        }
    }

    @Override
    public ResponseEntity<String> reportComment(Integer commentId, Integer reporterId, StudyRecruitReportCreateDTO request) {
        try {
            // 1. 신고자 존재 여부 확인
            Member reporter = memberRepository.findById(reporterId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

            // 2. 신고할 댓글 존재 여부 확인 (공개상태만)
            StudyRecruitComment studyRecruitComment = studyRecruitCommentRepository
                    .findByIdAndVisibility(commentId, "Y")
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않거나 공개되지 않은 댓글입니다."));

            // 3. 중복 신고 확인
            if (studyRecruitReportRepository.existsByReporterIdAndStudyRecruitCommentId(reporterId, commentId)) {
                return ResponseEntity.badRequest().body("이미 신고한 댓글입니다.");
            }

            // 4. 자기 자신의 댓글 신고 방지
            if (studyRecruitComment.getMember().getId().equals(reporterId)) {
                return ResponseEntity.badRequest().body("본인이 작성한 댓글은 신고할 수 없습니다.");
            }

            // 5. 신고 Entity 생성 및 저장
            StudyRecruitReport report = new StudyRecruitReport(
                    reporter,
                    studyRecruitComment,
                    request.getReason(),
                    DateTimeUtils.nowDateTime()
            );

            studyRecruitReportRepository.save(report);

            return ResponseEntity.status(HttpStatus.CREATED).body("댓글 신고가 완료되었습니다.");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 신고 중 오류가 발생했습니다.");
        }
    }
}