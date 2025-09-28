package com.threego.algo.algorithm.command.domain.aggregate;

import com.threego.algo.algorithm.command.application.dto.MemberAlgoCorrectQuizHistoryId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "member_algo_correct_quiz_history")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MemberAlgoCorrectQuizHistory {
    @EmbeddedId
    private MemberAlgoCorrectQuizHistoryId id;
}