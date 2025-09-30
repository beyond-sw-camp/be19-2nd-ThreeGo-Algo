package com.threego.algomemberservice.member.query.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminMemberDetailResponseDTO {
    private int id;
    private String email;
    private String nickname;
    private String rank;
    private String role;
    private int point;
    private String status;
    private int reportedCount;
    private String createdAt;
}
