package com.threego.algo.algorithm.query.service;

import com.threego.algo.algorithm.command.domain.aggregate.AlgoRoadmap;
import com.threego.algo.algorithm.query.dao.AlgoMapper;
import com.threego.algo.algorithm.query.dto.AlgoMemberSolvedQuizResponseDTO;
import com.threego.algo.algorithm.query.dto.AlgoPostCommentDTO;
import com.threego.algo.algorithm.query.dto.AlgoPostDetailResponseDTO;
import com.threego.algo.algorithm.query.dto.AlgoPostSummaryResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public List<AlgoPostCommentDTO> findCommentsByPostId(final int postId) {
        final List<AlgoPostCommentDTO> comments = algoMapper.selectCommentsByPostId(postId);

        final List<AlgoPostCommentDTO> commentResponse = comments.stream()
                .filter((comment) -> comment.getParentCommentId() == null)
                .collect(Collectors.toList());

        final Map<Integer, List<AlgoPostCommentDTO>> commentMap = new LinkedHashMap<>();

        comments.forEach((comment) -> {
            if (comment.getParentCommentId() == null) {
                commentMap.put(comment.getId(), new ArrayList<>());
            } else {
                commentMap.get(comment.getParentCommentId()).add(comment);
            }
        });

        commentResponse.forEach((comment) -> {
            comment.setChildComments(commentMap.get(comment.getId()));
        });

        return commentResponse;
    }

    @Override
    public AlgoPostDetailResponseDTO findAlgoPostByPostId(final int memberId, final int postId) {
        final AlgoPostDetailResponseDTO postResponse = algoMapper.selectAlgoPostByPostId(memberId, postId);

        // TODO 조회된 게시물의 삭제 여부가 'N'인 경우 예외 처리
        final List<String> imageResponse = algoMapper.selectAlgoImagesByPostId(postId);
        postResponse.setImageUrls(imageResponse);

        return postResponse;
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
