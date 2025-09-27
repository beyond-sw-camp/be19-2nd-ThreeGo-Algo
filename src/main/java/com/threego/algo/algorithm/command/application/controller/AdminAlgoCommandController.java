package com.threego.algo.algorithm.command.application.controller;

import com.threego.algo.algorithm.command.application.dto.AlgoPostDetailResponseDTO;
import com.threego.algo.algorithm.command.application.dto.AlgoPostRequestDTO;
import com.threego.algo.algorithm.command.application.dto.AlgoRoadmapRequestDTO;
import com.threego.algo.algorithm.command.application.dto.AlgoRoadmapResponseDTO;
import com.threego.algo.algorithm.command.application.service.AlgoCommandService;
import com.threego.algo.algorithm.command.domain.aggregate.AlgoRoadmap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin/algo")
@RestController
public class AdminAlgoCommandController {
    private final AlgoCommandService algoCommandService;

    @Autowired
    public AdminAlgoCommandController(AlgoCommandService algoCommandService) {
        this.algoCommandService = algoCommandService;
    }

    @PostMapping("/roadmaps")
    public ResponseEntity<AlgoRoadmapResponseDTO> createAlgoRoadmap(@RequestBody final AlgoRoadmapRequestDTO request) {
        final AlgoRoadmap algoRoadmap = algoCommandService.createAlgoRoadmap(request);

        final AlgoRoadmapResponseDTO response = AlgoRoadmapResponseDTO.of(algoRoadmap);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/roadmaps/{roadmapId}")
    public ResponseEntity<AlgoRoadmapResponseDTO> updateAlgoRoadmap(@PathVariable("roadmapId") final int roadmapId,
                                                                    @RequestBody final AlgoRoadmapRequestDTO request) throws Exception {
        final AlgoRoadmap algoRoadmap = algoCommandService.updateAlgoRoadmap(roadmapId, request);

        final AlgoRoadmapResponseDTO response = AlgoRoadmapResponseDTO.of(algoRoadmap);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/roadmaps/{roadmapId}/posts")
    public ResponseEntity<AlgoPostDetailResponseDTO> createAlgoPost(@PathVariable("roadmapId") int roadmapId,
                                                                    @RequestBody final AlgoPostRequestDTO request) throws Exception {
        // TODO. memberID는 Authentication에서 받아오도록 수정 필요
        final AlgoPostDetailResponseDTO response = algoCommandService.createAlgoPost(29, roadmapId, request);

        return ResponseEntity.ok(response);
    }
}