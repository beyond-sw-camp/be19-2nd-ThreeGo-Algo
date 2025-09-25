package com.threego.algo.career.query.service;

import com.threego.algo.career.query.dto.PostSummaryResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CareerInfoService{

    List<PostSummaryResponseDto> findPosts();
}
