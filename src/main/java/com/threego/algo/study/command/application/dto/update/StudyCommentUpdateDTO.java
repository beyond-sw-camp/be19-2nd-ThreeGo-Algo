package com.threego.algo.study.command.application.dto.update;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class StudyCommentUpdateDTO {

    @NotBlank(message = "댓글 내용은 필수입니다.")
    @Size(max = 500, message = "댓글은 500자 이하여야 합니다.")
    private String content;

    public StudyCommentUpdateDTO(String content) {
        this.content = content;
    }
}