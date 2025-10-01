package com.threego.algo.study.command.domain.repository;

import com.threego.algo.study.command.domain.aggregate.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRepository extends JpaRepository<Study, Integer> {

    /* 설명. 모집글 ID로 스터디 존재 여부 확인 (중복 생성 방지용) */
    boolean existsByRecruitPostId(int recruitPostId);
}