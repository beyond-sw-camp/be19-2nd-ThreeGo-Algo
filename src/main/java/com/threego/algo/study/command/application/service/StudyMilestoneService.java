package com.threego.algo.study.command.application.service;

import com.threego.algo.study.command.application.dto.create.StudyMilestoneCreateDTO;
import com.threego.algo.study.command.application.dto.update.StudyMilestoneUpdateDTO;
import org.springframework.http.ResponseEntity;

public interface StudyMilestoneService {
    /* 설명. 마일스톤 등록 */
    ResponseEntity<String> createMilestone(int roadmapId, int leaderId, StudyMilestoneCreateDTO request);

    /* 설명. 마일스톤 수정 */
    ResponseEntity<String> updateMilestone(int milestoneId, int leaderId, StudyMilestoneUpdateDTO request);

    /* 설명. 마일스톤 삭제 */
    ResponseEntity<String> deleteMilestone(int milestoneId, int leaderId);

}
