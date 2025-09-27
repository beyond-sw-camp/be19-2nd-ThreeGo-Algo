package com.threego.algo.algorithm.command.domain.repository;

import com.threego.algo.algorithm.command.domain.aggregate.AlgoPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlgoPostCommandRepository extends JpaRepository<AlgoPost, Integer> {
}