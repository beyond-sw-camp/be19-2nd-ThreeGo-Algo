package com.threego.algo.algorithm.command.domain.repository;

import com.threego.algo.algorithm.command.domain.aggregate.AlgoPost;
import com.threego.algo.algorithm.command.domain.aggregate.AlgoPostImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlgoPostImageCommandRepository extends JpaRepository<AlgoPostImage, Integer> {
    List<AlgoPostImage> findByAlgoPost(final AlgoPost algoPost);
}