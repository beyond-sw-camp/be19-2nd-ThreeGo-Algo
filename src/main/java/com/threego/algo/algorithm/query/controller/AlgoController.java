package com.threego.algo.algorithm.query.controller;

import com.threego.algo.algorithm.query.dto.AlgoRoadmapResponseDTO;
import com.threego.algo.algorithm.query.service.AlgoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Algorithm", description = "회원용 알고리즘 학습 API")
@RequestMapping("/algo")
@RestController
public class AlgoController {
    private final AlgoService algoService;

    @Autowired
    public AlgoController(AlgoService algoService) {
        this.algoService = algoService;
    }

    @Operation(summary = "전체 알고리즘 학습 로드맵 조회", description = "회원이 전체 알고리즘 학습 로드맵을 확인할 수 있는 API입니다.")
    @GetMapping("/roadmaps")
    public ResponseEntity<List<AlgoRoadmapResponseDTO>> findAllAlgoRoadMaps() {
        List<AlgoRoadmapResponseDTO> response = algoService.findAllAlgoRoadmaps().stream()
                .map(AlgoRoadmapResponseDTO::of)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}