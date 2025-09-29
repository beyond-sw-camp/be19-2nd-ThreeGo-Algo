package com.threego.algo.coding.command.domain.repository;

import com.threego.algo.coding.command.domain.aggregate.CodingComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodingCommentRepository extends JpaRepository<CodingComment, Integer> {}
