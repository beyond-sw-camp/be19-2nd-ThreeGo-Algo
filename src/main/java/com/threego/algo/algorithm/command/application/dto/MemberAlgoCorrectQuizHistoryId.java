package com.threego.algo.algorithm.command.application.dto;

import com.threego.algo.algorithm.command.domain.aggregate.AlgoQuizQuestion;
import com.threego.algo.member.command.domain.aggregate.Member;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class MemberAlgoCorrectQuizHistoryId {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "algo_quiz_question_id", nullable = false)
    private AlgoQuizQuestion algoQuizQuestion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberAlgoCorrectQuizHistoryId that = (MemberAlgoCorrectQuizHistoryId) o;
        return Objects.equals(member, that.member) && Objects.equals(algoQuizQuestion, that.algoQuizQuestion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(member, algoQuizQuestion);
    }
}