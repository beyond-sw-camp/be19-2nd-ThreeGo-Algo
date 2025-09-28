package com.threego.algo.study.command.application.service;

import com.threego.algo.study.command.application.dto.create.StudyCommentCreateDTO;
import com.threego.algo.study.command.application.dto.create.StudyReportCreateDTO;
import com.threego.algo.study.command.application.dto.update.StudyCommentUpdateDTO;
import org.springframework.http.ResponseEntity;

public interface StudyCommentService {
    ResponseEntity<String> createComment(Integer postId, Integer memberId, StudyCommentCreateDTO request);

    ResponseEntity<String> updateComment(Integer commentId, Integer memberId, StudyCommentUpdateDTO request);

    ResponseEntity<String> deleteComment(Integer commentId, Integer memberId);

    ResponseEntity<String> adminDeleteComment(Integer commentId, Integer adminId);
}