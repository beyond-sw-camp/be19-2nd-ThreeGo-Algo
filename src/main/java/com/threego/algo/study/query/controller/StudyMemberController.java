package com.threego.algo.study.query.controller;

import com.threego.algo.study.query.dto.StudyMemberDTO;
import com.threego.algo.study.query.service.StudyMemberService;
import com.threego.algo.study.query.service.StudyMemberServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Tag(name = "Study Member", description = "스터디 멤버 목록 조회 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/study")
public class StudyMemberController {

    private final StudyMemberService studyMemberService;

    @Operation(
            summary = "스터디 멤버 목록 조회",
            description = "스터디 그룹의 전체 멤버 목록을 조회합니다."
    )
    @GetMapping("/{studyId}/members")
    public ResponseEntity<List<StudyMemberDTO>> findMembers(@PathVariable Integer studyId) {
        List<StudyMemberDTO> members = studyMemberService.findAllStudyMember(studyId);
        return ResponseEntity.ok(members);
    }
}