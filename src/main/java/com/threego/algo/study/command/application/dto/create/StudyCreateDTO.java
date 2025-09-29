package com.threego.algo.study.command.application.dto.create;

import com.threego.algo.common.constants.ValidationConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyCreateDTO {



    @NotBlank(message = "스터디명은 필수입니다.")
    @Size(max = 100, message = "스터디명은 100자 이하여야 합니다.")
    private String name;

    @NotBlank(message = "스터디 소개는 필수입니다.")
    @Size(max = 500, message = "스터디 소개는 500자 이하여야 합니다.")
    private String description;

    @NotBlank(message = "시작일은 필수입니다.")
    @Pattern(regexp = ValidationConstants.DATE_PATTERN, message = ValidationConstants.DATE_FORMAT_MESSAGE)
    private String startDate;

    @Pattern(regexp = ValidationConstants.DATE_PATTERN, message = ValidationConstants.DATE_FORMAT_MESSAGE)
    private String endDate; // NULL 허용, 필수 아님
}