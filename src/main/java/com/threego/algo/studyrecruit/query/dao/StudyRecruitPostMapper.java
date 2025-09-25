package com.threego.algo.studyrecruit.query.dao;

import com.threego.algo.studyrecruit.query.dto.StudyRecruitDetailDTO;
import com.threego.algo.studyrecruit.query.dto.StudyRecruitPostDTO;
import com.threego.algo.studyrecruit.query.dto.StudyRecruitSearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudyRecruitPostMapper {

    /* 설명. 스터디 모집글 전체 목록 조회 */
    List<StudyRecruitPostDTO> selectStudyRecruitList(StudyRecruitSearchDTO searchDto);

    /* 설명. 스터디 모집글 상세 조회 */
    StudyRecruitDetailDTO selectStudyRecruitDetail(Long id);


}
