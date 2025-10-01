package com.threego.algo.member.query.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDetailResponseDTO {
    private int id;
    private String email;
    private String nickname;
    private String rank;
    private int point;
    private int attendanceCount;
    private String createdAt;
}
