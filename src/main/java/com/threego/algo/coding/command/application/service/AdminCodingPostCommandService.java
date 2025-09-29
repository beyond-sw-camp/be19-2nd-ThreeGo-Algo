package com.threego.algo.coding.command.application.service;

import com.threego.algo.coding.command.application.dto.CodingCommentRequestDTO;
import com.threego.algo.coding.command.application.dto.CodingPostRequestDTO;
import com.threego.algo.coding.command.application.dto.CodingProblemRequestDTO;
import com.threego.algo.coding.command.domain.aggregate.CodingComment;
import com.threego.algo.coding.command.domain.aggregate.CodingPost;
import com.threego.algo.coding.command.domain.aggregate.CodingProblem;

public interface AdminCodingPostCommandService {

    CodingPost updatePost(int postId, CodingPostRequestDTO dto);

    void deletePost(int postId);

    CodingComment updateComment(int commentId, CodingCommentRequestDTO dto);

    void deleteComment(int commentId);

    CodingProblem createProblem(CodingProblemRequestDTO dto);

    CodingProblem updateProblem(int problemId, CodingProblemRequestDTO dto);

    void deleteProblem(int problemId);
}