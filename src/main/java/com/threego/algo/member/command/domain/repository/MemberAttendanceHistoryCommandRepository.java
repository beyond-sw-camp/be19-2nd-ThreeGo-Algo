package com.threego.algo.member.command.domain.repository;

import com.threego.algo.member.command.domain.aggregate.Member;
import com.threego.algo.member.command.domain.aggregate.MemberAttendanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberAttendanceHistoryCommandRepository extends JpaRepository<MemberAttendanceHistory, Integer> {
    boolean existsByMemberAndAttendAt(Member member, String today);
}
