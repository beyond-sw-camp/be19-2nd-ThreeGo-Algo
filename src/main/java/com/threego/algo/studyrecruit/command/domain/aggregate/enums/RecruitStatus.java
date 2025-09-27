package com.threego.algo.studyrecruit.command.domain.aggregate.enums;

import lombok.Getter;

@Getter
public enum RecruitStatus {
    OPEN("OPEN", "모집중"),
    CLOSED("CLOSED", "모집완료"),
    EXPIRED("EXPIRED", "모집기간만료");

    private final String code;
    private final String displayName;

    RecruitStatus(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}