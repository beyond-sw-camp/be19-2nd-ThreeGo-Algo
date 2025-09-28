package com.threego.algo.member.command.domain.aggregate;

import com.threego.algo.member.command.domain.aggregate.enums.RoleName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Table(name = "Role")
@NoArgsConstructor
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleName name;

    @Column(nullable = false)
    private String description;

    public boolean isAdmin() {
        return this.name == RoleName.ADMIN;
    }

    public boolean isMember() {
        return this.name == RoleName.MEMBER;
    }
}