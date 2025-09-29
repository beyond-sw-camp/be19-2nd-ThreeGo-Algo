package com.threego.algo.member.command.domain.repository;

import com.threego.algo.member.command.domain.aggregate.MemberRank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRankRepository extends JpaRepository<MemberRank, Integer> {
}
