package com.threego.algo.studyrecruit.command.application.service;

import com.threego.algo.studyrecruit.command.application.dto.create.StudyRecruitPostCreateDTO;
import com.threego.algo.studyrecruit.command.application.dto.update.StudyRecruitPostUpdateDTO;
import org.springframework.http.ResponseEntity;

public interface StudyRecruitPostService {

    /* 설명. 모집글 등록 */
    ResponseEntity<String> createPost(Integer memberId, StudyRecruitPostCreateDTO request);

    /* 설명. 모집글 수정 */
    ResponseEntity<String> updatePost(Integer postId, Integer memberId, StudyRecruitPostUpdateDTO request);

    /* 설명. 모집글 삭제 */
    ResponseEntity<String> deletePost(Integer postId, Integer memberId);

    /* 설명. 모집 마감 */
    ResponseEntity<String> closeRecruitment(Integer postId, Integer memberId);
}