package com.threego.algo.coding.command.domain.repository;

import com.threego.algo.coding.command.domain.aggregate.CodingPostImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodingPostImageRepository extends JpaRepository<CodingPostImage, Integer> {}