package com.threego.algo.career.command.application.controller;

import com.threego.algo.career.command.application.dto.CareerInfoPostCreateRequest;
import com.threego.algo.career.command.application.service.CareerInfoCommandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Career Info", description = "회원용 기업별 정보 공유 API")
@RestController
@RequestMapping("/career-info")
public class CareerInfoCommandController {
    private final CareerInfoCommandService service;

    @Autowired
    public CareerInfoCommandController(CareerInfoCommandService service) {
        this.service = service;
    }

    @Operation(
            summary = "게시물 등록",
            description = "회원이 기업별 정보 공유 게시판에 게시물을 등록합니다."
    )
    @PostMapping("/posts")
    public ResponseEntity<Integer> createPost(@RequestBody CareerInfoPostCreateRequest request) {
        Integer postId = service.createPost(request);
        return ResponseEntity.ok(postId);
    }
}
