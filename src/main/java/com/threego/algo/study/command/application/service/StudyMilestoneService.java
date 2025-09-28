package com.threego.algo.study.command.application.service;

import com.threego.algo.study.command.application.dto.create.StudyMilestoneCreateDTO;
import com.threego.algo.study.command.application.dto.update.StudyMilestoneUpdateDTO;
import org.springframework.http.ResponseEntity;

public interface StudyMilestoneService {
    /* 설명. 마일스톤 등록 */
    ResponseEntity<String> createMilestone(Integer roadmapId, Integer leaderId, StudyMilestoneCreateDTO request);

    /* 설명. 마일스톤 수정 */
    ResponseEntity<String> updateMilestone(Integer milestoneId, Integer leaderId, StudyMilestoneUpdateDTO request);

    /* 설명. 마일스톤 삭제 */
    ResponseEntity<String> deleteMilestone(Integer milestoneId, Integer leaderId);

}
