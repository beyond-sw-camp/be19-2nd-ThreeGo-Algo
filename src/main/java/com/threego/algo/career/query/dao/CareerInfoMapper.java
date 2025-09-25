package com.threego.algo.career.query.dao;

import com.threego.algo.career.command.domain.aggregate.enums.Status;
import com.threego.algo.career.query.dto.PostSummaryResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CareerInfoMapper {
    List<PostSummaryResponseDto> findPosts(
            @Param("visibility") String visibility,
            @Param("status") Status status,
            @Param("keyword") String keyword);
}
