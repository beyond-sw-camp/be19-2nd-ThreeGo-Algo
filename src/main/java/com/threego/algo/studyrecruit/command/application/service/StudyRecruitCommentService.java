package com.threego.algo.studyrecruit.command.application.service;

import com.threego.algo.studyrecruit.command.application.dto.create.StudyRecruitCommentCreateDTO;
import com.threego.algo.studyrecruit.command.application.dto.update.StudyRecruitCommentUpdateDTO;
import org.springframework.http.ResponseEntity;

public interface StudyRecruitCommentService {

    /* 설명. 댓글 등록 */
    ResponseEntity<String> createComment(int postId, int memberId, StudyRecruitCommentCreateDTO request);

    /* 설명. 댓글 수정 */
    ResponseEntity<String> updateComment(int commentId, int memberId, StudyRecruitCommentUpdateDTO request);

    /* 설명. 댓글 삭제(소프트 딜리트) */
    ResponseEntity<String> deleteComment(int commentId, int memberId);

    /* 설명. 관리자 댓글 삭제 */
    ResponseEntity<String> adminDeleteComment(int commentId, int adminId);
}