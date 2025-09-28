package com.threego.algo.career.command.application.controller;

import com.threego.algo.career.command.application.service.AdminCareerCommandService;
import com.threego.algo.career.command.domain.aggregate.enums.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Career Info (Admin)", description = "관리자용 기업별 정보 공유 API")
@RestController
@RequestMapping("/admin/career-info")
public class AdminCareerCommandController {
    private final AdminCareerCommandService adminService;

    @Autowired
    public AdminCareerCommandController(AdminCareerCommandService adminService) {
        this.adminService = adminService;
    }

    @Operation(summary = "게시물 상태 변경 (관리자)")
    @PutMapping("/posts/{postId}/status")
    public ResponseEntity<Void> updatePostStatus(
            @PathVariable int postId,
            @RequestParam Status status,
            @RequestParam(required = false) String rejectReason
    ) {
        adminService.updatePostStatus(postId, status, rejectReason);
        return ResponseEntity.ok().build();
    }
}
