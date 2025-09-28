package com.threego.algo.study.command.domain.repository;

import com.threego.algo.study.command.domain.aggregate.StudyComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyCommentRepository extends JpaRepository<StudyComment, Integer> {
}