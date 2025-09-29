package com.threego.algo.coding.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodingPostRequestDTO {
    private int memberId;
    private int problemId;
    private String title;
    private String content;
}
