package com.threego.algo.coding.command.domain.repository;

import com.threego.algo.coding.command.domain.aggregate.CodingProblem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodingProblemRepository extends JpaRepository<CodingProblem, Integer> {}
