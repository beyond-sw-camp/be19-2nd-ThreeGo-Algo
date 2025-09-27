package com.threego.algo.studyrecruit.command.application.service;

import org.springframework.http.ResponseEntity;

public interface StudyRecruitMemberService {

    /* 설명. 참가 신청 */
    ResponseEntity<String> applyToStudy(Integer postId, Integer memberId);

    /* 설명. 참가 신청 취소 */
    ResponseEntity<String> cancelApplication(Integer joinId, Integer memberId);

    /* 설명. 참가 신청 승인 */
    ResponseEntity<String> acceptApplication(Integer joinId, Integer authorId);

    /* 설명. 참가 신청 거절 */
    ResponseEntity<String> rejectApplication(Integer joinId, Integer authorId);
}