package com.threego.algo.studyrecruit.command.application.service;

import com.threego.algo.studyrecruit.command.application.dto.create.StudyRecruitPostCreateDTO;
import com.threego.algo.studyrecruit.command.application.dto.update.StudyRecruitPostUpdateDTO;
import com.threego.algo.studyrecruit.query.dto.StudyRecruitMemberDTO;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudyRecruitPostService {

    /* 설명. 모집글 등록 */
    ResponseEntity<String> createPost(int memberId, StudyRecruitPostCreateDTO request);

    /* 설명. 모집글 수정 */
    ResponseEntity<String> updatePost(int postId, int memberId, StudyRecruitPostUpdateDTO request);

    /* 설명. 모집글 삭제 */
    ResponseEntity<String> deletePost(int postId, int memberId);

    /* 설명. 모집 마감 */
    ResponseEntity<String> closeRecruitment(int postId, int memberId);

    List<StudyRecruitMemberDTO> findStudyRecruitMembers(@NotNull(message = "모집글 ID는 필수입니다.") int recruitPostId);

    // 모집 게시물 소프트 딜리트
    ResponseEntity<String> adminDeletePost(int postId, int adminId);
}