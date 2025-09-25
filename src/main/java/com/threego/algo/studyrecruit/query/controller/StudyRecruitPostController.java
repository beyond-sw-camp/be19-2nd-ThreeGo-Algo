package com.threego.algo.studyrecruit.query.controller;

import com.threego.algo.studyrecruit.query.dto.StudyRecruitDetailDTO;
import com.threego.algo.studyrecruit.query.dto.StudyRecruitPostDTO;
import com.threego.algo.studyrecruit.query.dto.StudyRecruitSearchDTO;
import com.threego.algo.studyrecruit.query.service.StudyRecruitPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Study Recruit Posts", description = "스터디 모집글 조회 API")
@RestController
@RequestMapping("/study-recruit")
public class StudyRecruitPostController {

    private final StudyRecruitPostService studyRecruitPostService;

    public StudyRecruitPostController(StudyRecruitPostService studyRecruitPostService) {
        this.studyRecruitPostService = studyRecruitPostService;
    }

    @Operation(
            summary = "스터디 모집글 목록 조회",
            description = "페이징과 필터링을 지원하는 스터디 모집글 목록을 조회합니다."
    )
    @GetMapping("/posts")
    public ResponseEntity<List<StudyRecruitPostDTO>> findPosts(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String keyword
    ) {
        StudyRecruitSearchDTO searchDto = StudyRecruitSearchDTO.builder()
                .page(page)
                .size(size)
                .status(status)
                .keyword(keyword)
                .build();

        return ResponseEntity.ok(studyRecruitPostService.findStudyRecruitList(searchDto));
    }

    @Operation(
            summary = "스터디 모집글 상세 조회",
            description = "POST_ID로 특정 스터디 모집글의 상세 정보를 조회합니다."
    )
    @GetMapping("/posts/{postId}")
    public ResponseEntity<StudyRecruitDetailDTO> findPost(@PathVariable Long postId) {
        return ResponseEntity.ok(studyRecruitPostService.findStudyRecruitDetail(postId));
    }

}