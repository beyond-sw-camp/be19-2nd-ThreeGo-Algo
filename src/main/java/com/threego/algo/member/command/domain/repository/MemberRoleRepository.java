package com.threego.algo.member.command.domain.repository;

import com.threego.algo.member.command.domain.aggregate.MemberRole;
import com.threego.algo.member.command.domain.aggregate.enums.RoleName;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRoleRepository extends JpaRepository<MemberRole, Integer> {

    // 멤버 ID로 권한 조회
    @Query(value = "SELECT role_id FROM Member_Role WHERE member_id = :memberId", nativeQuery = true)
    Integer getRoleIdByMemberId(@Param("memberId") Integer memberId);
}