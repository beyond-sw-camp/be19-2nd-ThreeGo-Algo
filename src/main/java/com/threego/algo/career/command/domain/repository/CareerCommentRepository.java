package com.threego.algo.career.command.domain.repository;

import com.threego.algo.career.command.domain.aggregate.CareerInfoComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerCommentRepository extends JpaRepository<CareerInfoComment, Integer> {
}
