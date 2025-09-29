package com.threego.algo.member.command.domain.repository;

import com.threego.algo.member.command.domain.aggregate.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRoleCommandRepository extends JpaRepository<MemberRole, Integer> {
}
