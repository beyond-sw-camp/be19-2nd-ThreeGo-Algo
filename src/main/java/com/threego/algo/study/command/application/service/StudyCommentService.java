package com.threego.algo.study.command.application.service;

import com.threego.algo.study.command.application.dto.create.StudyCommentCreateDTO;
import com.threego.algo.study.command.application.dto.create.StudyReportCreateDTO;
import com.threego.algo.study.command.application.dto.update.StudyCommentUpdateDTO;
import org.springframework.http.ResponseEntity;

public interface StudyCommentService {
    ResponseEntity<String> createComment(int postId, int memberId, StudyCommentCreateDTO request);

    ResponseEntity<String> updateComment(int commentId, int memberId, StudyCommentUpdateDTO request);

    ResponseEntity<String> deleteComment(int commentId, int memberId);

    ResponseEntity<String> adminDeleteComment(int commentId, int adminId);
}