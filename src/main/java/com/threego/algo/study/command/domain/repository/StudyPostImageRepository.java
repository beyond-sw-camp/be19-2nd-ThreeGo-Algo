package com.threego.algo.study.command.domain.repository;

import com.threego.algo.study.command.domain.aggregate.StudyPostImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyPostImageRepository extends JpaRepository<StudyPostImage, Integer> {
    void deleteByPostId(int postId);

    List<StudyPostImage> findByPostId(int postId);
}