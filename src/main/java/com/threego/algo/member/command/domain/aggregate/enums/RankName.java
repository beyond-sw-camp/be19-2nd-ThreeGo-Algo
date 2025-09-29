package com.threego.algo.member.command.domain.aggregate.enums;

import lombok.Getter;

@Getter
public enum RankName {
    코알못("코알못"),
    코뉴비("코뉴비"),
    코좀알("코좀알"),
    코잘알("코잘알"),
    코신("코신");

    private final String displayName;

    RankName(String displayName) {
        this.displayName = displayName;
    }
}