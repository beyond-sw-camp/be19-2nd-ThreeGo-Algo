package com.threego.algo.career.command.domain.aggregate;

import com.threego.algo.common.util.DateTimeUtils;
import com.threego.algo.member.command.domain.aggregate.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "Career_Info_Comment")
public class CareerInfoComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private CareerInfoComment parent;  // 부모 댓글

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private CareerInfoPost post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(nullable = false, length = 1)
    private String visibility = "Y";

    @Column(name = "created_at", nullable = false, length = 20)
    private String createdAt;

    @Column(name = "updated_at", length = 20)
    private String updatedAt;

    public CareerInfoComment(CareerInfoComment parent, CareerInfoPost post, Member member, String content) {
        this.parent = parent;
        this.post = post;
        this.member = member;
        this.content = content;
        this.createdAt = DateTimeUtils.nowDateTime();
    }

    public void updateComment(String content) {
        this.content = content;
        this.updatedAt = DateTimeUtils.nowDateTime();
    }

    public void deleteComment() {
        this.visibility = "N";
    }
}
