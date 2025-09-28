package com.threego.algo.member.command.domain.aggregate;

import com.threego.algo.auth.command.application.dto.UserDTO;
import com.threego.algo.common.util.DateTimeUtils;
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

    @Column(columnDefinition = "integer default 1")
    private int rankId;

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
        this.rankId = 1;
        this.status = Status.ACTIVE;
        this.createdAt = createdAt;
    }

    public static Member UserToMember(UserDTO dto) {
        return new Member(
            dto.getEmail(),
            dto.getPassword(),
            dto.getNickname(),
            DateTimeUtils.nowDateTime()
        );
    }



}