package com.threego.algo.study.command.application.dto.create;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class StudyCommentCreateDTO {

    private Integer parentId; // 대댓글일 경우만 값 존재

    @NotBlank(message = "댓글 내용은 필수입니다.")
    @Size(max = 500, message = "댓글은 500자 이하여야 합니다.")
    private String content;

    public StudyCommentCreateDTO(String content) {
        this.content = content;
    }

    public StudyCommentCreateDTO(Integer parentId, String content) {
        this.parentId = parentId;
        this.content = content;
    }
}
