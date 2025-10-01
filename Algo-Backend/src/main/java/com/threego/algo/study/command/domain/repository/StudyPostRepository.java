package com.threego.algo.study.command.domain.repository;

import com.threego.algo.study.command.domain.aggregate.StudyPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyPostRepository extends JpaRepository<StudyPost, Integer> {

}