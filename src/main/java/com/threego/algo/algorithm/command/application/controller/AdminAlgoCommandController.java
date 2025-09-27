package com.threego.algo.algorithm.command.application.controller;

import com.threego.algo.algorithm.command.application.dto.AlgoPostDetailResponseDTO;
import com.threego.algo.algorithm.command.application.dto.AlgoPostRequestDTO;
import com.threego.algo.algorithm.command.application.dto.AlgoRoadmapRequestDTO;
import com.threego.algo.algorithm.command.application.dto.AlgoRoadmapResponseDTO;
import com.threego.algo.algorithm.command.application.service.AlgoCommandService;
import com.threego.algo.algorithm.command.domain.aggregate.AlgoRoadmap;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Admin Algorithm - Command", description = "관리자용 알고리즘 학습 API (Command)")
@RequestMapping("/admin/algo")
@RestController
public class AdminAlgoCommandController {
    private final AlgoCommandService algoCommandService;

    @Autowired
    public AdminAlgoCommandController(AlgoCommandService algoCommandService) {
        this.algoCommandService = algoCommandService;
    }

    @Operation(summary = "알고리즘 학습 로드맵 등록", description = "관리자가 알고리즘 학습 로드맵을 등록하는 API입니다.")
    @PostMapping("/roadmaps")
    public ResponseEntity<AlgoRoadmapResponseDTO> createAlgoRoadmap(@RequestBody final AlgoRoadmapRequestDTO request) {
        final AlgoRoadmap algoRoadmap = algoCommandService.createAlgoRoadmap(request);

        final AlgoRoadmapResponseDTO response = AlgoRoadmapResponseDTO.of(algoRoadmap);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "알고리즘 학습 로드맵 수정", description = "관리자가 알고리즘 학습 로드맵을 수정하는 API입니다.")
    @PutMapping("/roadmaps/{roadmapId}")
    public ResponseEntity<AlgoRoadmapResponseDTO> updateAlgoRoadmap(@PathVariable("roadmapId") final int roadmapId,
                                                                    @RequestBody final AlgoRoadmapRequestDTO request) throws Exception {
        final AlgoRoadmap algoRoadmap = algoCommandService.updateAlgoRoadmap(roadmapId, request);

        final AlgoRoadmapResponseDTO response = AlgoRoadmapResponseDTO.of(algoRoadmap);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "알고리즘 학습 게시물 등록", description = "관리자가 알고리즘 학습 게시물을 등록하는 API입니다.")
    @PostMapping("/roadmaps/{roadmapId}/posts")
    public ResponseEntity<AlgoPostDetailResponseDTO> createAlgoPost(@PathVariable("roadmapId") int roadmapId,
                                                                    @RequestBody final AlgoPostRequestDTO request) throws Exception {
        // TODO. memberID는 Authentication에서 받아오도록 수정 필요
        final AlgoPostDetailResponseDTO response = algoCommandService.createAlgoPost(29, roadmapId, request);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "알고리즘 학습 게시물 삭제", description = "관리자가 알고리즘 학습 게시물을 삭제하는 API입니다.")
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Void> deleteAlgoPost(@PathVariable("postId") final int postId) throws Exception {
        algoCommandService.deleteAlgoPost(postId);

        return ResponseEntity.noContent().build();
    }
}