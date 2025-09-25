package com.threego.algo.career.query.dao;

import com.threego.algo.career.query.dto.PostSummaryResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CareerInfoMapper {
    List<PostSummaryResponseDto> findPosts();
}
