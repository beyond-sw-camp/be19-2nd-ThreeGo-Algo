package com.threego.algo.algorithm.command.domain.repository;

import com.threego.algo.algorithm.command.domain.aggregate.AlgoQuizOption;
import com.threego.algo.algorithm.command.domain.aggregate.AlgoQuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlgoQuizOptionCommandRepository extends JpaRepository<AlgoQuizOption, Integer> {
    List<AlgoQuizOption> findByAlgoQuizQuestion(final AlgoQuizQuestion algoQuizQuestion);

    void deleteByAlgoQuizQuestion(final AlgoQuizQuestion algoQuizQuestion);
}