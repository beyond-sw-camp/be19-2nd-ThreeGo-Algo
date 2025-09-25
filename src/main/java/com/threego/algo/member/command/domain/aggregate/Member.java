package com.threego.algo.member.command.domain.aggregate;

import com.threego.algo.member.command.domain.aggregate.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Setter
@Getter
@Table(name = "Member")
@NoArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    public Member(final String email, final String password, final String nickname, final String createdAt) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.point = 0;
        this.reportedCount = 0;
        this.status = Status.ACTIVE;
        this.createdAt = createdAt;
    }
}