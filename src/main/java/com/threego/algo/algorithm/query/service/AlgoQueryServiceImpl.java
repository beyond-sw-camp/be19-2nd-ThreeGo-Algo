package com.threego.algo.algorithm.query.service;

import com.threego.algo.algorithm.command.domain.aggregate.AlgoRoadmap;
import com.threego.algo.algorithm.query.dao.AlgoMapper;
import com.threego.algo.algorithm.query.dto.AlgoMemberSolvedQuizResponseDTO;
import com.threego.algo.algorithm.query.dto.AlgoPostSummaryResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        final List<AlgoPostSummaryResponseDTO> postResponse = algoMapper.selectAlgoPosts(memberId, roadmapId, keyword, "Y");

        findSolvedQuizzesByMemberIdAndRoadmapIds(memberId, roadmapId, postResponse);

        return postResponse;
    }

    @Override
    public List<AlgoPostSummaryResponseDTO> findAlgoPostsByRoadmapIdForAdmin(final int roadmapId,
                                                                             final String keyword,
                                                                             final String visibility) {
        return algoMapper.selectAlgoPosts(null, roadmapId, keyword, visibility);
    }

    private void findSolvedQuizzesByMemberIdAndRoadmapIds(final int memberId, final int roadmapId,
                                                          final List<AlgoPostSummaryResponseDTO> postResponse) {
        final List<AlgoMemberSolvedQuizResponseDTO> memberSolvedQuizResponse =
                algoMapper.selectSolvedQuizzesByMemberIdAndRoadmapIds(memberId, roadmapId);

        final Map<Integer, List<Integer>> responseMap = new HashMap<>();

        memberSolvedQuizResponse.forEach((dto) -> responseMap.computeIfAbsent(dto.getAlgoPostId(), k -> new ArrayList<>())
                .add(dto.getAlgoQuizQuestionId()));

        postResponse.forEach((dto) -> {
            if (responseMap.get(dto.getPostId()) != null) {
                dto.setSolvedQuizIds(responseMap.get(dto.getPostId()));
            }
        });
    }
}
