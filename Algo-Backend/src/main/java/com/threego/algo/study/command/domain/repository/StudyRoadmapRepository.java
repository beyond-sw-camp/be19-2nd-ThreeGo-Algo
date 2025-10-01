package com.threego.algo.study.command.domain.repository;

import com.threego.algo.study.command.domain.aggregate.StudyRoadmap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudyRoadmapRepository extends JpaRepository<StudyRoadmap, Integer> {

}
