package com.threego.algo.algorithm.query.dao;

import com.threego.algo.algorithm.command.domain.aggregate.AlgoRoadmap;
import com.threego.algo.algorithm.query.dto.AlgoPostSummaryResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AlgoMapper {
    List<AlgoRoadmap> selectAllAlgoRoadmaps();

    List<AlgoPostSummaryResponseDTO> selectAlgoPostByRoadmapId(@Param("memberId") final int memberId,
                                                               @Param("roadmapId") final int roadmapId,
                                                               @Param("keyword") final String keyword);

    List<AlgoPostSummaryResponseDTO> selectAllAlgoPosts(final String keyword);
}