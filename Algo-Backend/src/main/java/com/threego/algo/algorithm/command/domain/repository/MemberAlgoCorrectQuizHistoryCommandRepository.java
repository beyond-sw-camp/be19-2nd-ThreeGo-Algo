package com.threego.algo.algorithm.command.domain.repository;

import com.threego.algo.algorithm.command.domain.aggregate.MemberAlgoCorrectQuizHistoryId;
import com.threego.algo.algorithm.command.domain.aggregate.MemberAlgoCorrectQuizHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberAlgoCorrectQuizHistoryCommandRepository extends
        JpaRepository<MemberAlgoCorrectQuizHistory, MemberAlgoCorrectQuizHistoryId> {
}