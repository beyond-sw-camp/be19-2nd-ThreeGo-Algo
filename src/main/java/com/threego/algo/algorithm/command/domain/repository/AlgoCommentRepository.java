package com.threego.algo.algorithm.command.domain.repository;

import com.threego.algo.algorithm.command.domain.aggregate.AlgoComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlgoCommentRepository extends JpaRepository<AlgoComment, Integer> {
}