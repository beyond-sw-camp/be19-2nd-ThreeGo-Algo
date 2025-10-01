package com.threego.algomemberservice.member.command.domain.repository;

import com.threego.algomemberservice.member.command.domain.aggregate.Member;
import com.threego.algomemberservice.member.command.domain.aggregate.MemberAttendanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberAttendanceHistoryCommandRepository extends JpaRepository<MemberAttendanceHistory, Integer> {
    boolean existsByMemberAndAttendAt(Member member, String today);
}
