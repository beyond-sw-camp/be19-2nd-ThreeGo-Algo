package com.threego.algo.algorithm.query.service;

import com.threego.algo.algorithm.command.domain.aggregate.AlgoRoadmap;
import com.threego.algo.algorithm.query.dto.AlgoPostSummaryResponseDTO;

import java.util.List;

public interface AlgoService {
    List<AlgoRoadmap> findAllAlgoRoadmaps();

    List<AlgoPostSummaryResponseDTO> findAllAlgoPosts(final String keyword);
}