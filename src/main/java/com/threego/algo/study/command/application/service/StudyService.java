package com.threego.algo.study.command.application.service;

import com.threego.algo.study.command.application.dto.create.StudyCreateDTO;
import com.threego.algo.study.command.application.dto.update.StudyUpdateDTO;
import org.springframework.http.ResponseEntity;

public interface StudyService {

    /* 설명. 모집글에서 스터디 그룹 생성 */
    ResponseEntity<String> createStudyFromRecruit(Integer authorId, Integer postId,StudyCreateDTO request);

    /* 설명. 스터디 정보 수정 */
    ResponseEntity<String> updateStudy(Integer studyId, Integer leaderId, StudyUpdateDTO request);

    /* 설명. 스터디 그룹 삭제 */
    ResponseEntity<String> deleteStudy(Integer studyId, Integer leaderId);
}
