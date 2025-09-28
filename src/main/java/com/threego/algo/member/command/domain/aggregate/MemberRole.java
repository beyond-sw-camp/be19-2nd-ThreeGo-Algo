package com.threego.algo.member.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Member_Role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "member_id", nullable = false)
    private Integer memberId;

    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    // Member와의 연관관계 (필요한 경우)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    private Member member;

    // Role과의 연관관계 (필요한 경우)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;

    // 생성자
    public MemberRole(Integer memberId, Integer roleId) {
        this.memberId = memberId;
        this.roleId = roleId;
    }

    public MemberRole(Member member, Role role) {
        this.member = member;
        this.role = role;
        this.memberId = member.getId();
        this.roleId = role.getId();
    }

    // 편의 메서드 - 일반 회원 권한 확인
    public boolean isMember() {
        if (this.role != null) {
            return this.role.isMember();
        }
        // Role이 로드되지 않은 경우, roleId로 직접 확인 (MEMBER의 ID가 1이라고 가정)
        return this.roleId == 1;
    }
}