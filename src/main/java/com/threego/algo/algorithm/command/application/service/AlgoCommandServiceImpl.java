package com.threego.algo.algorithm.command.application.service;

import com.threego.algo.algorithm.command.application.dto.AlgoRoadmapRequestDTO;
import com.threego.algo.algorithm.command.domain.aggregate.AlgoRoadmap;
import com.threego.algo.algorithm.command.domain.repository.AlgoRoadmapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlgoCommandServiceImpl implements AlgoCommandService {
    private final AlgoRoadmapRepository algoRoadmapRepository;

    @Autowired
    public AlgoCommandServiceImpl(AlgoRoadmapRepository algoRoadmapRepository) {
        this.algoRoadmapRepository = algoRoadmapRepository;
    }

    @Override
    public AlgoRoadmap createAlgoRoadmap(final AlgoRoadmapRequestDTO request) {
        validAlgoRoadmapTitle(request.getTitle());

        final AlgoRoadmap algoRoadmap = new AlgoRoadmap(request.getTitle(), request.getDescription(),
                request.getOrder());

        return algoRoadmapRepository.save(algoRoadmap);
    }

    @Transactional
    @Override
    public AlgoRoadmap updateAlgoRoadmap(final int roadmapId, final AlgoRoadmapRequestDTO request) throws Exception {
        // TODO 커스텀 예외 발생 및 처리 필요
        final AlgoRoadmap algoRoadmap = algoRoadmapRepository.findById(roadmapId).orElseThrow(Exception::new);

        if (!algoRoadmap.getTitle().equals(request.getTitle())) {
            validAlgoRoadmapTitle(request.getTitle());
        }

        if (!algoRoadmap.getTitle().equals(request.getTitle())
                || !algoRoadmap.getDescription().equals(request.getDescription())
                || algoRoadmap.getOrder() != request.getOrder()) {
            algoRoadmap.updateAlgoRoadmap(request.getTitle(), request.getDescription(), request.getOrder());
        }

        return algoRoadmap;
    }

    private void validAlgoRoadmapTitle(final String title) {
        if (algoRoadmapRepository.existsByTitle(title)) {
            throw new RuntimeException("제목을 가진 로드맵이 이미 존재합니다.");
        }
    }
}