package com.threego.algo.studyrecruit.command.application.dto.create;

import com.threego.algo.common.constants.ValidationConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyRecruitPostCreateDTO {

    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 255, message = "제목은 255자 이하여야 합니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    @NotBlank(message = "시작일은 필수입니다.")
    @Pattern(regexp = ValidationConstants.DATE_PATTERN, message = ValidationConstants.DATE_TIME_FORMAT_MESSAGE)
    private String startDate;

    @Pattern(regexp = ValidationConstants.DATE_PATTERN , message =ValidationConstants.DATE_TIME_FORMAT_MESSAGE)
    private String endDate;

    @NotBlank(message = "모집 마감일시는 필수입니다.")
    @Pattern(regexp = ValidationConstants.DATE_TIME_PATTERN , message = ValidationConstants.DATE_TIME_FORMAT_MESSAGE)
    private String expiresAt;

    @NotNull(message = "모집 인원은 필수입니다.")
    @Min(value = 2, message = "모집 인원은 최소 2명 이상이어야 합니다.")
    @Max(value = 20, message = "모집 인원은 최대 20명까지 가능합니다.")
    private Integer capacity;

    @NotNull(message = "공개여부는 필수입니다.")
    @Schema( defaultValue = "Y", example = "Y")
    @Column(name = "visibility", columnDefinition = "char(1) default 'Y'")
    private String visibility; // Y(공개) 또는 N(비공개)
}