package com.threego.algo.studyrecruit.command.domain.aggregate;

import com.threego.algo.common.util.DateTimeUtils;
import com.threego.algo.member.command.domain.aggregate.Member;
import com.threego.algo.studyrecruit.command.domain.aggregate.enums.VisibilityStatus;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "Study_Recruit_Comment")
@Getter
@Setter
@NoArgsConstructor
public class StudyRecruitComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private StudyRecruitPost studyRecruitPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private StudyRecruitComment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudyRecruitComment> replies = new ArrayList<>();

    @Column(nullable = false, length = 500)
    private String content;

    @Column(name = "created_at", nullable = false)
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(columnDefinition = "char(1) default 'Y'")
    @Enumerated(EnumType.STRING)
    private VisibilityStatus visibility;

    // 생성자 - 일반 댓글
    public StudyRecruitComment(StudyRecruitPost studyRecruitPost, Member member,
                               String content, String createdAt) {
        this.studyRecruitPost = studyRecruitPost;
        this.member = member;
        this.content = content;
        this.createdAt = createdAt;
        this.visibility = VisibilityStatus.Y;
    }

    // 생성자 - 대댓글
    public StudyRecruitComment(StudyRecruitPost studyRecruitPost, Member member,
                               StudyRecruitComment parent, String content, String createdAt) {
        this.studyRecruitPost = studyRecruitPost;
        this.member = member;
        this.parent = parent;
        this.content = content;
        this.createdAt = createdAt;
        this.visibility = VisibilityStatus.Y;
    }

    // 비즈니스 메소드

    /* 설명. 댓글 소프트 딜리트 */
    public void softDelete() {
        this.visibility = VisibilityStatus.N;
        this.updatedAt = DateTimeUtils.nowDateTime();
    }

    /* 설명. 댓글 수정 */
    public void updateComment(String content) {
        this.content = content;
        this.updatedAt = DateTimeUtils.nowDateTime();
    }
}