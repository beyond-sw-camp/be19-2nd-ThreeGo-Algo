package com.threego.algo.studyrecruit.command.application.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyRecruitCommentCreateDTO {

    @NotBlank(message = "댓글 내용은 필수입니다.")
    @Size(max = 500, message = "댓글은 500자 이하여야 합니다.")
    private String content;

    private Integer parentId; // 대댓글인 경우 부모 댓글 ID (null이면 일반 댓글)
}