package com.threego.algo.member.command.application.service;

import com.threego.algo.member.command.domain.aggregate.enums.RoleName;
import com.threego.algo.member.command.domain.aggregate.enums.Status;

public interface AdminMemberCommandService {
    void updateMemberStatus(int memberId, Status status);
    void updateMemberRole(int memberId, RoleName roleName);
}
