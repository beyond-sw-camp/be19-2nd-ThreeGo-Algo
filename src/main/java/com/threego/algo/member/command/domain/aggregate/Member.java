package com.threego.algo.member.command.domain.aggregate;

import com.threego.algo.member.command.domain.aggregate.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Table(name = "Member")
@NoArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(columnDefinition = "integer default 0")
    private int point;

    @Column(name = "reported_count", columnDefinition = "integer default 0")
    private int reportedCount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at", nullable = false)
    private String createdAt;

    // Role과의 다대다 관계
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Member_Role",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    // MemberRank와의 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rank_id", nullable = false)
    private MemberRank memberRank;

    public Member(final String email, final String password, final String nickname, final MemberRank memberRank, final String createdAt) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.memberRank = memberRank;
        this.point = 0;
        this.reportedCount = 0;
        this.status = Status.ACTIVE;
        this.createdAt = createdAt;
    }
}