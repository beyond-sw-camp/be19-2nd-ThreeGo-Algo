package com.threego.algo.study.command.application.dto.create;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class StudyPostCreateDTO {

    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 255, message = "제목은 255자 이하여야 합니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    public StudyPostCreateDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }
}