package com.threego.algo.algorithm.command.domain.repository;

import com.threego.algo.algorithm.command.domain.aggregate.AlgoQuizOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlgoQuizOptionCommandRepository extends JpaRepository<AlgoQuizOption, Integer> {
}