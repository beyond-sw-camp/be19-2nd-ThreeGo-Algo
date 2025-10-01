package com.threego.algomemberservice.member.command.domain.aggregate.enums;

import lombok.Getter;

@Getter
public enum RoleName {
    MEMBER("MEMBER", "일반 회원"),
    ADMIN("ADMIN", "관리자");

    private final String code;
    private final String displayName;

    RoleName(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}