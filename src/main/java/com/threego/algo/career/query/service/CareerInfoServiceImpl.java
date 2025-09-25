package com.threego.algo.career.query.service;

import com.threego.algo.career.command.domain.aggregate.enums.Status;
import com.threego.algo.career.query.dao.CareerInfoMapper;
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
}
