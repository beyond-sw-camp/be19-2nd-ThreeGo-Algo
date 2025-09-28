package com.threego.algo.coding.command.application.controller;

import com.threego.algo.coding.command.application.dto.CodingCommentRequestDTO;
import com.threego.algo.coding.command.application.dto.CodingPostImageRequestDTO;
import com.threego.algo.coding.command.application.dto.CodingPostRequestDTO;
import com.threego.algo.coding.command.domain.service.CodingPostCommandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Coding Post- Command", description = "회원용 코딩게시물 API (Command)")
@RestController
@RequiredArgsConstructor
@RequestMapping("/coding")
public class CodingPostCommandController {

    private final CodingPostCommandService codingPostCommandService;

    // 게시물 등록
    @PostMapping("/posts")
    public ResponseEntity<Integer> createPost(@RequestBody CodingPostRequestDTO request) {
        int id = codingPostCommandService.createPost(request);
        return ResponseEntity.ok(id);
    }

    // 게시물 수정
    @PutMapping("/posts/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable int postId, @RequestBody CodingPostRequestDTO request) {
        codingPostCommandService.updatePost(postId, request);
        return ResponseEntity.ok("수정 완료");
    }

    // 게시물 삭제 (회원용 soft-delete)
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable int postId) {
        codingPostCommandService.softDeletePost(postId);
        return ResponseEntity.ok("삭제 완료");
    }

    // 게시물 이미지 등록
    @PostMapping("/posts/{postId}/images")
    public ResponseEntity<Integer> addImage(@PathVariable int postId, @RequestBody CodingPostImageRequestDTO request) {
        int id = codingPostCommandService.addImage(postId, request);
        return ResponseEntity.ok(id);
    }

    // 댓글 등록 (parentId가 있으면 대댓글)
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Integer> createComment(@PathVariable int postId, @RequestBody CodingCommentRequestDTO request) {
        int id = codingPostCommandService.addComment(postId, request);
        return ResponseEntity.ok(id);
    }

    // 댓글 수정
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable int commentId, @RequestBody CodingCommentRequestDTO request) {
        codingPostCommandService.updateComment(commentId, request);
        return ResponseEntity.ok("수정 완료");
    }

    // 댓글 삭제 (회원용 soft-delete)
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable int commentId) {
        codingPostCommandService.softDeleteComment(commentId);
        return ResponseEntity.ok("삭제 완료");
    }
}
