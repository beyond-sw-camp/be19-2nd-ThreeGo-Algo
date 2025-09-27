package com.threego.algo.studyrecruit.command.domain.aggregate.enums;

import lombok.Getter;

@Getter
public enum VisibilityStatus {
    Y("Y", "공개"),
    N("N", "비공개/삭제");

    private final String code;
    private final String displayName;

    VisibilityStatus(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}