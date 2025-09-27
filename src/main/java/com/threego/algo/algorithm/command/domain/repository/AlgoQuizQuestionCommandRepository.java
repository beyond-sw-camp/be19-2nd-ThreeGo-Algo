package com.threego.algo.algorithm.command.domain.repository;

import com.threego.algo.algorithm.command.domain.aggregate.AlgoPost;
import com.threego.algo.algorithm.command.domain.aggregate.AlgoQuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlgoQuizQuestionCommandRepository extends JpaRepository<AlgoQuizQuestion, Integer> {
    int countByAlgoPost(final AlgoPost algoPost);
}