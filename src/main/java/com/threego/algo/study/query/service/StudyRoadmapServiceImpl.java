package com.threego.algo.study.query.service;

import com.threego.algo.study.query.dao.StudyRoadmapMapper;
import com.threego.algo.study.query.dto.StudyMemberDTO;
import com.threego.algo.study.query.dto.StudyRoadmapDTO;
import com.threego.algo.study.query.dto.StudyRoadmapDetailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudyRoadmapServiceImpl implements StudyRoadmapService {
    private final StudyRoadmapMapper studyRoadmapMapper;

    @Override
    public List<StudyRoadmapDTO> findAllStudyRoadmap(Integer studyId) {
        return studyRoadmapMapper.selectAllStudyRoadmap(studyId);
    }

    @Override
    public StudyRoadmapDetailDTO findStudyRoadmapDetail(Integer roadmapId) {
        return studyRoadmapMapper.selectStudyRoadmapDetail(roadmapId);
    }
}
