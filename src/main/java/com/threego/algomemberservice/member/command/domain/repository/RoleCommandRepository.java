package com.threego.algomemberservice.member.command.domain.repository;

import com.threego.algomemberservice.member.command.domain.aggregate.Member;
import com.threego.algomemberservice.member.command.domain.aggregate.Role;
import com.threego.algomemberservice.member.command.domain.aggregate.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleCommandRepository  extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleName roleName);
}
