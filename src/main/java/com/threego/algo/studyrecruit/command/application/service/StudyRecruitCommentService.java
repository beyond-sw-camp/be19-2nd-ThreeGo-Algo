package com.threego.algo.studyrecruit.command.application.service;

import com.threego.algo.studyrecruit.command.application.dto.create.StudyRecruitCommentCreateDTO;
import com.threego.algo.studyrecruit.command.application.dto.update.StudyRecruitCommentUpdateDTO;
import org.springframework.http.ResponseEntity;

public interface StudyRecruitCommentService {

    /* 설명. 댓글 등록 */
    ResponseEntity<String> createComment(Integer postId, Integer memberId, StudyRecruitCommentCreateDTO request);

    /* 설명. 댓글 수정 */
    ResponseEntity<String> updateComment(Integer commentId, Integer memberId, StudyRecruitCommentUpdateDTO request);

    /* 설명. 댓글 삭제(소프트 딜리트) */
    ResponseEntity<String> deleteComment(Integer commentId, Integer memberId);

    /* 설명. 관리자 댓글 삭제 */
    ResponseEntity<String> adminDeleteComment(Integer commentId, Integer adminId);
}