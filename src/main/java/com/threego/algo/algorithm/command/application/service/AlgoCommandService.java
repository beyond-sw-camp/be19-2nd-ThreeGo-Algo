package com.threego.algo.algorithm.command.application.service;

import com.threego.algo.algorithm.command.application.dto.AlgoCommentRequestDTO;
import com.threego.algo.algorithm.command.application.dto.AlgoPostDetailResponseDTO;
import com.threego.algo.algorithm.command.application.dto.AlgoPostRequestDTO;
import com.threego.algo.algorithm.command.application.dto.AlgoRoadmapRequestDTO;
import com.threego.algo.algorithm.command.domain.aggregate.AlgoRoadmap;

public interface AlgoCommandService {
    AlgoRoadmap createAlgoRoadmap(final AlgoRoadmapRequestDTO request);

    AlgoRoadmap updateAlgoRoadmap(final int roadmapId, final AlgoRoadmapRequestDTO request) throws Exception;

    AlgoPostDetailResponseDTO createAlgoPost(final int memberId, final int roadmapId, final AlgoPostRequestDTO request) throws Exception;

    void deleteAlgoPost(final int postId) throws Exception;

    void createComment(final int memberId, final int postId,final AlgoCommentRequestDTO request) throws Exception;

    void updateComment(final int memberId, final int commentId, final AlgoCommentRequestDTO request) throws Exception;
}