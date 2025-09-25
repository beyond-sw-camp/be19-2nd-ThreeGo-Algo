package com.threego.algo.algorithm.query.controller;

import com.threego.algo.algorithm.query.dto.AlgoPostSummaryResponseDTO;
import com.threego.algo.algorithm.query.service.AlgoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Admin Algorithm", description = "관리자용 알고리즘 학습 API")
@RequestMapping("/admin/algo")
@RestController
public class AdminAlgoController {
    private final AlgoService algoService;

    @Autowired
    public AdminAlgoController(AlgoService algoService) {
        this.algoService = algoService;
    }

    @GetMapping("/posts")
    public ResponseEntity<List<AlgoPostSummaryResponseDTO>> findAllPosts(@RequestParam(required = false) final String keyword) {
        final List<AlgoPostSummaryResponseDTO> response = algoService.findAllAlgoPosts(keyword);

        return ResponseEntity.ok(response);
    }
}