package com.threego.algo.studyrecruit.query.service;

import com.threego.algo.studyrecruit.query.dto.*;

import java.util.List;

public interface StudyRecruitPostQueryService {
    List<StudyRecruitPostDTO> findStudyRecruitList(StudyRecruitSearchDTO searchDto);

    StudyRecruitDetailDTO findStudyRecruitDetail(Integer postId);

    List<StudyRecruitCommentDTO> findStudyRecruitComments(Integer postId);

    List<StudyRecruitMemberDTO> findStudyRecruitMembers(Integer postId);

    List<StudyRecruitPostDTO> findStudyRecruitListIncludeHidden(StudyRecruitSearchDTO searchDto);

    StudyRecruitDetailDTO findStudyRecruitDetailIncludeHidden(Integer postId);

    List<StudyRecruitCommentDTO> findAllStudyRecruitCommentsIncludeHidden(StudyRecruitSearchDTO searchDto);

    List<StudyRecruitCommentDTO> findStudyRecruitCommentsIncludeHidden(Integer postId);
}
