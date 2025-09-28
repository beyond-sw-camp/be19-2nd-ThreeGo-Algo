package com.threego.algo.study.command.application.service;

import org.springframework.http.ResponseEntity;

public interface StudyMemberService {
    // 그룹원 강퇴
    ResponseEntity<String> kickMember(Integer studyId, Integer memberId, Integer leaderId);

    // 그룹장 권한 위임
    ResponseEntity<String> delegateLeadership(Integer studyId, Integer newLeaderId, Integer currentLeaderId);
}
