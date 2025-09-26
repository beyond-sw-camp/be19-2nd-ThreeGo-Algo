package com.threego.algo.study.query.controller;

import com.threego.algo.study.query.dto.StudyCommentDTO;
import com.threego.algo.study.query.dto.StudyPostDTO;
import com.threego.algo.study.query.dto.StudyPostDetailDTO;
import com.threego.algo.study.query.dto.StudyPostSearchDTO;
import com.threego.algo.study.query.service.StudyPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Study Post", description = "스터디 게시물 조회 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/study")
public class StudyPostController {

    private final StudyPostService studyPostService;

    @Operation(
            summary = "스터디 게시물 목록 조회",
            description = "스터디 그룹의 게시물 목록을 페이징으로 조회합니다."
    )
    @GetMapping("/{studyId}/posts")
    public ResponseEntity<List<StudyPostDTO>> findPosts(
            @PathVariable Integer studyId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        StudyPostSearchDTO searchDto = StudyPostSearchDTO.builder()
                .studyId(studyId)
                .page(page)
                .size(size)
                .build();

        List<StudyPostDTO> posts = studyPostService.findAllStudyPosts(searchDto);
        return ResponseEntity.ok(posts);
    }

    @Operation(
            summary = "스터디 게시물 상세 조회",
            description = "스터디 게시물의 상세 정보와 이미지를 조회합니다."
    )
    @GetMapping("/posts/{postId}")
    public ResponseEntity<StudyPostDetailDTO> findPostDetail(@PathVariable Integer postId) {
        StudyPostDetailDTO postDetail = studyPostService.findStudyPostDetail(postId);
        return ResponseEntity.ok(postDetail);
    }

    @Operation(
            summary = "스터디 게시물 댓글 조회",
            description = "스터디 게시물의 댓글들을 조회합니다."
    )
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<StudyCommentDTO>> findPostComments(@PathVariable Integer postId) {
        List<StudyCommentDTO> comments = studyPostService.findStudyPostComments(postId);
        return ResponseEntity.ok(comments);
    }

}
