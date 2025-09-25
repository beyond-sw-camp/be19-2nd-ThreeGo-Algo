package com.threego.algo.algorithm.query.service;

import com.threego.algo.algorithm.command.domain.aggregate.AlgoRoadmap;
import com.threego.algo.algorithm.query.dao.AlgoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlgoService {
    private final AlgoMapper algoMapper;

    @Autowired
    public AlgoService(AlgoMapper algoMapper) {
        this.algoMapper = algoMapper;
    }

    public List<AlgoRoadmap> findAllAlgoRoadmaps() {
        return algoMapper.findAllAlgoRoadmaps();
    }
}
