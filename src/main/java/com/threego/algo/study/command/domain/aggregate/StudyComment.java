package com.threego.algo.study.command.domain.aggregate;

import com.threego.algo.common.util.DateTimeUtils;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Study_Comment")
@Getter
@Setter
@NoArgsConstructor
public class StudyComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "post_id", nullable = false)
    private int postId;

    @Column(name = "member_id", nullable = false)
    private int memberId;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(name = "created_at", nullable = false, length = 20)
    private String createdAt;

    @Column(name = "updated_at", length = 20)
    private String updatedAt;

    @Column(nullable = false, length = 1)
    private String visibility = "Y";

    // 생성자
    public StudyComment(int postId, int memberId, String content) {
        this.postId = postId;
        this.memberId = memberId;
        this.content = content;
        this.visibility = "Y";
        this.createdAt = DateTimeUtils.nowDateTime();
    }

    // 대댓글 생성자
    public StudyComment(int postId, int memberId, Integer parentId, String content) {
        this.postId = postId;
        this.memberId = memberId;
        this.parentId = parentId;
        this.content = content;
        this.visibility = "Y";
        this.createdAt = DateTimeUtils.nowDateTime();
    }

    // 비즈니스 메소드
    public void updateComment(String content) {
        this.content = content;
        this.updatedAt = DateTimeUtils.nowDateTime();
    }


    public void softDelete() {
        this.visibility = "N";
        this.updatedAt = DateTimeUtils.nowDateTime();
    }
}