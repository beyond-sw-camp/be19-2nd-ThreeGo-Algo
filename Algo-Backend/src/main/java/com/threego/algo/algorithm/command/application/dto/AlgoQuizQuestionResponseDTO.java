package com.threego.algo.algorithm.command.application.dto;

import com.threego.algo.algorithm.command.domain.aggregate.AlgoQuizQuestion;
import com.threego.algo.algorithm.command.domain.aggregate.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlgoQuizQuestionResponseDTO {
    private int id;
    private String question;
    private Type type;
    private List<AlgoQuizOptionResponseDTO> options;

    public static AlgoQuizQuestionResponseDTO of(final AlgoQuizQuestion quizQuestion,
                                                 final List<AlgoQuizOptionResponseDTO> options) {
        return new AlgoQuizQuestionResponseDTO(quizQuestion.getId(), quizQuestion.getQuestion(), quizQuestion.getType()
                , options);
    }
}