package com.threego.algo.studyrecruit.command.domain.aggregate;

import com.threego.algo.member.command.domain.aggregate.Member;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "Study_Recruit_Report")
@Getter
@Setter
@NoArgsConstructor
public class StudyRecruitReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id", nullable = false)
    private Member reporter; // 신고자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private StudyRecruitPost studyRecruitPost; // 신고된 게시물

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private StudyRecruitComment studyRecruitComment; // 신고된 댓글

    @Column(nullable = false, length = 500)
    private String reason; // 신고 사유

    @Column(name = "created_at", nullable = false)
    private String createdAt;

    // 생성자 - 게시물 신고
    public StudyRecruitReport(Member reporter, StudyRecruitPost studyRecruitPost,
                              String reason, String createdAt) {
        this.reporter = reporter;
        this.studyRecruitPost = studyRecruitPost;
        this.reason = reason;
        this.createdAt = createdAt;
    }

    // 생성자 - 댓글 신고
    public StudyRecruitReport(Member reporter, StudyRecruitComment studyRecruitComment,
                              String reason, String createdAt) {
        this.reporter = reporter;
        this.studyRecruitComment = studyRecruitComment;
        this.reason = reason;
        this.createdAt = createdAt;
    }
}