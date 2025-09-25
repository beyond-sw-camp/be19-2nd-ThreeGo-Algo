package com.threego.algo.career.query.service;

import com.threego.algo.career.command.domain.aggregate.enums.Status;
import com.threego.algo.career.query.dao.CareerInfoMapper;
import com.threego.algo.career.query.dto.PostDetailResponseDto;
import com.threego.algo.career.query.dto.PostSummaryResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareerInfoServiceImpl implements CareerInfoService{
    private final CareerInfoMapper careerInfoMapper;

    @Autowired
    public CareerInfoServiceImpl(CareerInfoMapper careerInfoMapper) {
        this.careerInfoMapper = careerInfoMapper;
    }

    @Override
    public List<PostSummaryResponseDto> findPosts(String visibility, Status status, String keyword) {
        return careerInfoMapper.findPosts(visibility, status, keyword);
    }

    @Override
    public PostDetailResponseDto findPostForMember(int postId) {
        PostDetailResponseDto dto = careerInfoMapper.selectPost(postId, "Y");
        dto.setImageUrl(null);  // 회원에게는 이미지 숨김
        return dto;
    }

    @Override
    public PostDetailResponseDto findPostForAdmin(int postId) {
        return careerInfoMapper.selectPost(postId, null);
    }

}
