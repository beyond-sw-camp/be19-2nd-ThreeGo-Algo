package com.threego.algomemberservice.member.command.application.service;

public interface MemberCommandService {
    void updateMemberInfo(int memberId, String newNickname);

    String createAttendance(int memberId);
}
