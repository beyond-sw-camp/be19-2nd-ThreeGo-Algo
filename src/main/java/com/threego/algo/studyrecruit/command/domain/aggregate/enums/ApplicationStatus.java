package com.threego.algo.studyrecruit.command.domain.aggregate.enums;

import lombok.Getter;

@Getter
public enum ApplicationStatus {
    PENDING("PENDING", "대기중"),
    APPROVED("APPROVED", "승인됨"),
    REJECTED("REJECTED", "거절됨");

    private final String code;
    private final String displayName;

    ApplicationStatus(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}