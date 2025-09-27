package com.threego.algo.algorithm.command.domain.repository;

import com.threego.algo.algorithm.command.domain.aggregate.AlgoPostImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlgoPostImageCommandRepository extends JpaRepository<AlgoPostImage, Integer> {
}