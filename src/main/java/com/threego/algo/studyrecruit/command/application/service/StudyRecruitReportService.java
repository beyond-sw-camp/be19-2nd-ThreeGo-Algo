package com.threego.algo.studyrecruit.command.application.service;

import com.threego.algo.studyrecruit.command.application.dto.create.StudyRecruitReportCreateDTO;
import org.springframework.http.ResponseEntity;

public interface StudyRecruitReportService {

    /* 설명. 모집글 신고 */
    ResponseEntity<String> reportPost(int postId, int reporterId, StudyRecruitReportCreateDTO request);

    /* 설명. 댓글 신고 */
    ResponseEntity<String> reportComment(int commentId, int reporterId, StudyRecruitReportCreateDTO request);
}