package com.threego.algo.studyrecruit.command.application.service;

import com.threego.algo.studyrecruit.command.application.dto.create.StudyRecruitReportCreateDTO;
import org.springframework.http.ResponseEntity;

public interface StudyRecruitReportService {

    /* 설명. 모집글 신고 */
    ResponseEntity<String> reportPost(Integer postId, Integer reporterId, StudyRecruitReportCreateDTO request);

    /* 설명. 댓글 신고 */
    ResponseEntity<String> reportComment(Integer commentId, Integer reporterId, StudyRecruitReportCreateDTO request);
}