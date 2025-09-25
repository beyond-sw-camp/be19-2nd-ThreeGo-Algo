package com.threego.algo.career.query.controller;

import com.threego.algo.career.query.dto.PostSummaryResponseDto;
import com.threego.algo.career.query.service.CareerInfoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Career Info", description = "기업별 정보 공유 API")
@RestController
@RequestMapping("/career-info")
public class CareerInfoController {

    private final CareerInfoService careerInfoService;

    @Autowired
    public CareerInfoController(CareerInfoService careerInfoService) {
        this.careerInfoService = careerInfoService;
    }

    @Operation(
            summary = "기업별 정보 공유 게시물 목록 조회",
            description = "기업별 정보 공유 게시물을 최신순으로 조회합니다. (목록)"
    )
    @GetMapping("/posts")
    public ResponseEntity<List<PostSummaryResponseDto>> findPosts() {
        return ResponseEntity.ok(careerInfoService.findPosts());
    }
}
