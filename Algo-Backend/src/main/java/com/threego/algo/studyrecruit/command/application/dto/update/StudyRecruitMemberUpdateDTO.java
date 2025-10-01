package com.threego.algo.studyrecruit.command.application.dto.update;

import com.threego.algo.studyrecruit.command.domain.aggregate.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyRecruitMemberUpdateDTO {

    @NotNull(message = "신청 상태는 필수입니다.")
    private ApplicationStatus status; // PENDING, APPROVED, REJECTED

    @Size(max = 500, message = "처리 메시지는 500자 이하여야 합니다.")
    private String responseMessage; // 승인/거절 시 메시지
}