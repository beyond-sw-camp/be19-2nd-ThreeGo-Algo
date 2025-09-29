package com.threego.algo.algorithm.command.domain.aggregate;

import com.threego.algo.algorithm.command.domain.aggregate.enums.Type;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "algo_quiz_question")
@NoArgsConstructor
@Entity
public class AlgoQuizQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "algo_post_id", nullable = false)
    private AlgoPost algoPost;

    public AlgoQuizQuestion(final String question, final Type type, final AlgoPost algoPost) {
        this.question = question;
        this.type = type;
        this.algoPost = algoPost;
    }

    public void updateQuestion(final String question) {
        this.question = question;
    }
}