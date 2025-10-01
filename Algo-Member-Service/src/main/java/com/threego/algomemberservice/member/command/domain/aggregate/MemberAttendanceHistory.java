package com.threego.algomemberservice.member.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Table(name = "Member_Attendance_History")
@NoArgsConstructor
@Entity
public class MemberAttendanceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String attendAt;

    public MemberAttendanceHistory(Member member, String attendAt) {
        this.member = member;
        this.attendAt = attendAt;
    }
}