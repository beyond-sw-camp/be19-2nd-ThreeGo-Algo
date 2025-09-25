package com.threego.algo.career.query.controller;

import com.threego.algo.career.command.domain.aggregate.enums.Status;
import com.threego.algo.career.query.dto.PostSummaryResponseDto;
import com.threego.algo.career.query.service.CareerInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Career Info (Admin)", description = "관리자용 기업별 정보 공유 API")
@RestController
@RequestMapping("/admin/career-info")
public class AdminCareerInfoController {
    private final CareerInfoService careerInfoService;

    @Autowired
    public AdminCareerInfoController(CareerInfoService careerInfoService) {
        this.careerInfoService = careerInfoService;
    }

    @Operation(
            summary = "관리자용 기업별 정보 공유 게시물 목록 조회",
            description = "관리자는 전체 게시물을 조회할 수 있으며, status, keyword 조건을 추가할 수 있습니다."
    )
    @GetMapping("/posts")
    public ResponseEntity<List<PostSummaryResponseDto>> findPosts(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) String keyword
    ) {
        return ResponseEntity.ok(careerInfoService.findPosts(null, status, keyword));
    }
}
