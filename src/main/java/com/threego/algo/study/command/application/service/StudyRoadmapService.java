package com.threego.algo.study.command.application.service;

import com.threego.algo.study.command.application.dto.create.StudyRoadmapCreateDTO;
import com.threego.algo.study.command.application.dto.update.StudyRoadmapUpdateDTO;
import org.springframework.http.ResponseEntity;

public interface StudyRoadmapService {

    /* 설명. 스터디 로드맵 등록 */
    ResponseEntity<String> createRoadmap(Integer studyId, Integer leaderId, StudyRoadmapCreateDTO request);

    /* 설명. 스터디 로드맵 수정 */
    ResponseEntity<String> updateRoadmap(Integer roadmapId, Integer leaderId, StudyRoadmapUpdateDTO request);

    /* 설명. 스터디 로드맵 삭제 */
    ResponseEntity<String> deleteRoadmap(Integer roadmapId, Integer leaderId);
}
