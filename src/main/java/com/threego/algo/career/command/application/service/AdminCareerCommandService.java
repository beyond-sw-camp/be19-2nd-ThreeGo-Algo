package com.threego.algo.career.command.application.service;

import com.threego.algo.career.command.domain.aggregate.enums.Status;

public interface AdminCareerCommandService {
    void updatePostStatus(int postId, Status status, String rejectReason);
}
