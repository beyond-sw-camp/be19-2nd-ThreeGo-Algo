package com.threego.algo.study.query.service;

import com.threego.algo.study.query.dto.StudyCommentDTO;
import com.threego.algo.study.query.dto.StudyPostDTO;
import com.threego.algo.study.query.dto.StudyPostDetailDTO;
import com.threego.algo.study.query.dto.StudyPostSearchDTO;

import java.util.List;

public interface StudyPostService {

    List<StudyPostDTO> findAllStudyPosts(StudyPostSearchDTO searchDto);

    StudyPostDetailDTO findStudyPostDetail(Integer postId);

    List<StudyCommentDTO> findStudyPostComments(Integer postId);
}
