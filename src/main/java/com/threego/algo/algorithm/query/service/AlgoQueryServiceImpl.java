package com.threego.algo.algorithm.query.service;

import com.threego.algo.algorithm.command.domain.aggregate.AlgoRoadmap;
import com.threego.algo.algorithm.query.dao.AlgoMapper;
import com.threego.algo.algorithm.query.dto.AlgoPostSummaryResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlgoQueryServiceImpl implements AlgoQueryService {
    private final AlgoMapper algoMapper;

    @Autowired
    public AlgoQueryServiceImpl(AlgoMapper algoMapper) {
        this.algoMapper = algoMapper;
    }

    @Override
    public List<AlgoRoadmap> findAllAlgoRoadmaps() {
        return algoMapper.selectAllAlgoRoadmaps();
    }

    @Override
    public List<AlgoPostSummaryResponseDTO> findAllAlgoPosts(final String keyword, final String visibility) {
        return algoMapper.selectAlgoPosts(null, null, keyword, visibility);
    }

    @Override
    public List<AlgoPostSummaryResponseDTO> findAlgoPostsByRoadmapId(final int memberId, final int roadmapId,
                                                                     final String keyword) {
        return algoMapper.selectAlgoPosts(memberId, roadmapId, keyword, "Y");
    }

    @Override
    public List<AlgoPostSummaryResponseDTO> findAlgoPostsByRoadmapIdForAdmin(final int roadmapId,
                                                                             final String keyword,
                                                                             final String visibility) {
        return algoMapper.selectAlgoPosts(null, roadmapId, keyword, visibility);
    }
}
