package com.threego.algomemberservice.member.command.domain.repository;

import com.threego.algomemberservice.member.command.domain.aggregate.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCommandRepository extends JpaRepository<Member, Integer> {
}
