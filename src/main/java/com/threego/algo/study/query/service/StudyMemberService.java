package com.threego.algo.study.query.service;

import com.threego.algo.study.query.dto.StudyMemberDTO;

import java.util.List;


public interface StudyMemberService {
    List<StudyMemberDTO> findAllStudyMember(Integer studyId);

}
