package com.threego.algo.studyrecruit.query.dao;

import com.threego.algo.studyrecruit.query.dto.StudyRecruitCommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudyRecruitCommentMapper {

    /* 설명. 스터디 모집글 댓글 목록 조회 */
    List<StudyRecruitCommentDTO> selectStudyRecruitComments(Long postId);
}