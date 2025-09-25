package com.threego.algo.studyrecruit.query.dao;

import com.threego.algo.studyrecruit.query.dto.StudyRecruitPostDto;
import com.threego.algo.studyrecruit.query.dto.StudyRecruitSearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudyRecruitPostMapper {

    /* 설명. 스터디 모집글 전체 목록 조회 */
    List<StudyRecruitPostDto> selectStudyRecruitList(StudyRecruitSearchDto searchDto);




}
