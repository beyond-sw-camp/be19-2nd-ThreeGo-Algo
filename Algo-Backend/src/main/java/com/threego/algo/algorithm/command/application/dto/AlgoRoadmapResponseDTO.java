package com.threego.algo.algorithm.command.application.dto;

import com.threego.algo.algorithm.command.domain.aggregate.AlgoRoadmap;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlgoRoadmapResponseDTO {
    private int id;
    private String title;
    private String description;
    private int order;
    private String createdAt;
    private String updatedAt;
    private int questionCount;

    public static AlgoRoadmapResponseDTO of(final AlgoRoadmap algoRoadmap) {
        return AlgoRoadmapResponseDTO.builder()
                .id(algoRoadmap.getId())
                .title(algoRoadmap.getTitle())
                .description(algoRoadmap.getDescription())
                .order(algoRoadmap.getOrder())
                .createdAt(algoRoadmap.getCreatedAt())
                .updatedAt(algoRoadmap.getUpdatedAt())
                .questionCount(algoRoadmap.getQuestionCount())
                .build();
    }
}