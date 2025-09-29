package com.threego.algo.studyrecruit.command.domain.repository;

import com.threego.algo.studyrecruit.command.domain.aggregate.StudyRecruitReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRecruitReportRepository extends JpaRepository<StudyRecruitReport, Integer> {

    // 중복 신고 방지 - 게시물 신고 여부 확인
    boolean existsByReporterIdAndStudyRecruitPostId(int reporterId, int postId);

    // 중복 신고 방지 - 댓글 신고 여부 확인
    boolean existsByReporterIdAndStudyRecruitCommentId(int reporterId, int commentId);
}
