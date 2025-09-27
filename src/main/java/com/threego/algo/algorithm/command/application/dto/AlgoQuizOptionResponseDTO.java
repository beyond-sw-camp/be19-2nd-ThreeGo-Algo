package com.threego.algo.algorithm.command.application.dto;

import com.threego.algo.algorithm.command.domain.aggregate.AlgoQuizOption;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlgoQuizOptionResponseDTO {
    private int id;
    private String optionText;
    private boolean isCorrect;

    public static AlgoQuizOptionResponseDTO of(final AlgoQuizOption quizOption) {
        return new AlgoQuizOptionResponseDTO(quizOption.getId(), quizOption.getOptionText(), quizOption.isCorrect());
    }
}