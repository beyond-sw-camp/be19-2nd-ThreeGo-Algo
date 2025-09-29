package com.threego.algo.coding.command.domain.repository;

import com.threego.algo.coding.command.domain.aggregate.CodingPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodingPostRepository extends JpaRepository<CodingPost, Integer> {}
