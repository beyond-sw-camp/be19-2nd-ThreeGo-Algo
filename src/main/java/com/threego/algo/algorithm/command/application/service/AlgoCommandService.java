package com.threego.algo.algorithm.command.application.service;

import com.threego.algo.algorithm.command.application.dto.AlgoRoadmapRequestDTO;
import com.threego.algo.algorithm.command.domain.aggregate.AlgoRoadmap;

public interface AlgoCommandService {
    AlgoRoadmap createdRoadmap(final AlgoRoadmapRequestDTO request);
}