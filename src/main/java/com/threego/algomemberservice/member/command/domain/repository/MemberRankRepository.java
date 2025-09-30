package com.threego.algomemberservice.member.command.domain.repository;

import com.threego.algomemberservice.member.command.domain.aggregate.MemberRank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRankRepository extends JpaRepository<MemberRank, Integer> {
}
