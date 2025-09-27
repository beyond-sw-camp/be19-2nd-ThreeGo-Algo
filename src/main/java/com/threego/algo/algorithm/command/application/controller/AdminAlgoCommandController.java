package com.threego.algo.algorithm.command.application.controller;

import com.threego.algo.algorithm.command.application.dto.AlgoRoadmapRequestDTO;
import com.threego.algo.algorithm.command.application.dto.AlgoRoadmapResponseDTO;
import com.threego.algo.algorithm.command.application.service.AlgoCommandService;
import com.threego.algo.algorithm.command.domain.aggregate.AlgoRoadmap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin/algo")
@RestController
public class AdminAlgoCommandController {
    private final AlgoCommandService algoCommandService;

    @Autowired
    public AdminAlgoCommandController(AlgoCommandService algoCommandService) {
        this.algoCommandService = algoCommandService;
    }

    @PostMapping("/roadmaps")
    public ResponseEntity<AlgoRoadmapResponseDTO> createdRoadmap(@RequestBody final AlgoRoadmapRequestDTO request) {
        final AlgoRoadmap algoRoadmap = algoCommandService.createdRoadmap(request);

        final AlgoRoadmapResponseDTO response = AlgoRoadmapResponseDTO.of(algoRoadmap);

        return ResponseEntity.ok()
                .body(response);
    }
}