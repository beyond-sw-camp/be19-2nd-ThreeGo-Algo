package com.threego.algo.algorithm.query.dao;

import com.threego.algo.algorithm.command.domain.aggregate.AlgoRoadmap;
import com.threego.algo.algorithm.query.dto.AlgoMemberSolvedQuizResponseDTO;
import com.threego.algo.algorithm.query.dto.AlgoPostCommentDTO;
import com.threego.algo.algorithm.query.dto.AlgoPostDetailResponseDTO;
import com.threego.algo.algorithm.query.dto.AlgoPostSummaryResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AlgoMapper {
    List<AlgoRoadmap> selectAllAlgoRoadmaps();

    List<AlgoPostSummaryResponseDTO> selectAlgoPosts(@Param("memberId") final Integer memberId,
                                                     @Param("roadmapId") final Integer roadmapId,
                                                     @Param("keyword") final String keyword,
                                                     @Param("visibility") final String visibility);

    List<AlgoMemberSolvedQuizResponseDTO> selectSolvedQuizzesByMemberIdAndRoadmapIds(@Param("memberId") final int memberId,
                                                                                     @Param("roadmapId") final int roadmapId);

    List<AlgoPostCommentDTO> selectCommentsByPostId(final int postId);

    AlgoPostDetailResponseDTO selectAlgoPostByPostId(@Param("memberId") final int memberId,
                                                     @Param("postId") final int postId);

    List<String> selectAlgoImagesByPostId(@Param("postId") int postId);
}