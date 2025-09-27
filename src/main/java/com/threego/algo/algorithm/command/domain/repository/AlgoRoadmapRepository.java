package com.threego.algo.algorithm.command.domain.repository;

import com.threego.algo.algorithm.command.domain.aggregate.AlgoRoadmap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlgoRoadmapRepository extends JpaRepository<AlgoRoadmap, Integer> {
    boolean existsByTitle(final String title);
}