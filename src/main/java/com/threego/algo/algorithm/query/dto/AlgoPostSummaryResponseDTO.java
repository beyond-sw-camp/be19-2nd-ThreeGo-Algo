package com.threego.algo.algorithm.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AlgoPostSummaryResponseDTO {
    private int id;
    private int memberId;
    private String nickname;
    private String title;
    private String createdAt;
    private String updatedAt;
    private int likeCount;
    private int commentCount;
}