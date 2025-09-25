package com.threego.algo.studyrecruit.query.service;

import com.threego.algo.studyrecruit.query.dao.StudyRecruitPostMapper;
import com.threego.algo.studyrecruit.query.dto.StudyRecruitDetailDTO;
import com.threego.algo.studyrecruit.query.dto.StudyRecruitPostDTO;
import com.threego.algo.studyrecruit.query.dto.StudyRecruitSearchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudyRecruitPostService {
    private final StudyRecruitPostMapper studyRecruitPostMapper;

    /* 설명. 스터디 모집글 전체 목록 조회 */
    public List<StudyRecruitPostDTO> findStudyRecruitList(StudyRecruitSearchDTO searchDto) {
        return studyRecruitPostMapper.selectStudyRecruitList(searchDto);
    }

    /* 설명. 스터디 모집글 상세 조회 */
    public StudyRecruitDetailDTO findStudyRecruitDetail(Long postId) {
        return studyRecruitPostMapper.selectStudyRecruitDetail(postId);
    }

}
