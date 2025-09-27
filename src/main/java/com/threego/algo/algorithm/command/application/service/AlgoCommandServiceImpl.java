package com.threego.algo.algorithm.command.application.service;

import com.threego.algo.algorithm.command.application.dto.AlgoRoadmapRequestDTO;
import com.threego.algo.algorithm.command.domain.aggregate.AlgoRoadmap;
import com.threego.algo.algorithm.command.domain.repository.AlgoRoadmapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlgoCommandServiceImpl implements AlgoCommandService {
    private final AlgoRoadmapRepository algoRoadmapRepository;

    @Autowired
    public AlgoCommandServiceImpl(AlgoRoadmapRepository algoRoadmapRepository) {
        this.algoRoadmapRepository = algoRoadmapRepository;
    }

    @Override
    public AlgoRoadmap createdRoadmap(final AlgoRoadmapRequestDTO request) {
        validAlgoRoadmapTitle(request.getTitle());

        final AlgoRoadmap algoRoadmap = new AlgoRoadmap(request.getTitle(), request.getDescription(),
                request.getOrder());

        return algoRoadmapRepository.save(algoRoadmap);
    }

    private void validAlgoRoadmapTitle(final String title) {
        if (algoRoadmapRepository.existsByTitle(title)) {
            throw new RuntimeException("제목을 가진 로드맵이 이미 존재합니다.");
        }
    }
}