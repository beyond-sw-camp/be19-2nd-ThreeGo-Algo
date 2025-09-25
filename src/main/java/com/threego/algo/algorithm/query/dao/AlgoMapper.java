package com.threego.algo.algorithm.query.dao;

import com.threego.algo.algorithm.command.domain.aggregate.AlgoRoadmap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AlgoMapper {
    List<AlgoRoadmap> findAllAlgoRoadmaps();
}