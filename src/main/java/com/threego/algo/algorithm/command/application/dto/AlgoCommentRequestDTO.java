package com.threego.algo.algorithm.command.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AlgoCommentRequestDTO {
    private Integer parentId;
    private String comment;
}