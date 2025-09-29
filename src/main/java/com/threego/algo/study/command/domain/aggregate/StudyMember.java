package com.threego.algo.study.command.domain.aggregate;

import com.threego.algo.common.util.DateTimeUtils;
import com.threego.algo.member.command.domain.aggregate.Member;
import com.threego.algo.study.command.domain.aggregate.enums.StudyRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Study_Member")
@Getter
@Setter
@NoArgsConstructor
public class StudyMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id", nullable = false)
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StudyRole role;

    // 생성자
    public StudyMember(Study study, Member member, StudyRole role) {
        this.study = study;
        this.member = member;
        this.role = role;
    }

    // 비즈니스 메소드
    public boolean isLeader() {
        return this.role == StudyRole.LEADER;
    }

    public boolean isMember() {
        return this.role == StudyRole.MEMBER;
    }

    // 스터디 멤버 권한 확인
    public boolean isActive() {
        return this.role == StudyRole.LEADER || this.role == StudyRole.MEMBER;
    }

    // 그룹장 변경 메서드
    public void changeRole(StudyRole newLeader) {
        if (newLeader == null) {
            throw new IllegalArgumentException("역할은 null일 수 없습니다.");
        }
        this.role = newLeader;
    }


    // 강퇴된 멤버를 NOT_MEMBER로 변경하는 메서드
    public void kickOut() {
        this.role = StudyRole.NOT_MEMBER;
    }

}