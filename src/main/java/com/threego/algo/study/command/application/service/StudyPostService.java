package com.threego.algo.study.command.application.service;

import com.threego.algo.study.command.application.dto.create.StudyPostCreateDTO;
import com.threego.algo.study.command.application.dto.create.StudyReportCreateDTO;
import com.threego.algo.study.command.application.dto.update.StudyPostUpdateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudyPostService {
    ResponseEntity<String> createPost(Integer studyId, Integer memberId, StudyPostCreateDTO request, List<MultipartFile> images);

    ResponseEntity<String> updatePost(Integer postId, Integer memberId, StudyPostUpdateDTO request);

    ResponseEntity<String> deletePost(Integer postId, Integer memberId);

    ResponseEntity<String> adminDeletePost(Integer postId, Integer adminId);
}
