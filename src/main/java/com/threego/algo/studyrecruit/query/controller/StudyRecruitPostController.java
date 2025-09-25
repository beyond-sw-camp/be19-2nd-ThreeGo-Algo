package com.threego.algo.studyrecruit.query.controller;

import com.threego.algo.studyrecruit.query.dto.StudyRecruitPostDto;
import com.threego.algo.studyrecruit.query.dto.StudyRecruitSearchDto;
import com.threego.algo.studyrecruit.query.service.StudyRecruitPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<StudyRecruitPostDto>> findPosts(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String keyword
    ) {
        StudyRecruitSearchDto searchDto = StudyRecruitSearchDto.builder()
                .page(page)
                .size(size)
                .status(status)
                .keyword(keyword)
                .build();

        return ResponseEntity.ok(studyRecruitPostService.findStudyRecruitList(searchDto));
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Controller is working!");
    }
}