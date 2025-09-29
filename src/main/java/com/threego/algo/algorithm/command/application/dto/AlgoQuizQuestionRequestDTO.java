package com.threego.algo.algorithm.command.application.dto;

import com.threego.algo.algorithm.command.domain.aggregate.enums.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class AlgoQuizQuestionRequestDTO {
    private String question;
    private Type type;
    private List<AlgoQuizOptionRequestDTO> options;
}