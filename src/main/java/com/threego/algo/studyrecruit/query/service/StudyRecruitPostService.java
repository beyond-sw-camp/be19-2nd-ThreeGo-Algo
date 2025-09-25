package com.threego.algo.studyrecruit.query.service;

import com.threego.algo.studyrecruit.query.dao.StudyRecruitPostMapper;
import com.threego.algo.studyrecruit.query.dto.StudyRecruitPostDto;
import com.threego.algo.studyrecruit.query.dto.StudyRecruitSearchDto;
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
    public List<StudyRecruitPostDto> findStudyRecruitList(StudyRecruitSearchDto searchDto) {
        return studyRecruitPostMapper.selectStudyRecruitList(searchDto);
    }


}
