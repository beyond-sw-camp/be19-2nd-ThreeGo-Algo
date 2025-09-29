package com.threego.algo.study.command.application.controller;

import com.threego.algo.study.command.application.dto.create.StudyCommentCreateDTO;
import com.threego.algo.study.command.application.dto.create.StudyPostCreateDTO;
import com.threego.algo.study.command.application.dto.update.StudyCommentUpdateDTO;
import com.threego.algo.study.command.application.dto.update.StudyPostUpdateDTO;
import com.threego.algo.study.command.application.service.StudyCommentService;
import com.threego.algo.study.command.application.service.StudyPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/study")
@RequiredArgsConstructor
@Tag(name = "Study Post API", description = "스터디 게시물 API")
public class StudyPostCommandController {

    private final StudyPostService studyPostService;

    // 이미지 없는 게시물 등록
    @PostMapping("/{studyId}/posts")
    @Operation(summary = "스터디 게시물 등록 (텍스트만)", description = "이미지 없이 텍스트만으로 게시물을 등록합니다.")
    public ResponseEntity<String> createPost(
            @Parameter(description = "스터디 ID", required = true, example = "1")
            @PathVariable int studyId,

            @Parameter(description = "게시물 정보", required = true)
            @RequestBody StudyPostCreateDTO postDto,

            @Parameter(description = "작성자 멤버 ID", required = true, example = "1")
            @RequestParam int memberId) {
        return studyPostService.createPost(studyId, memberId, postDto, null);
    }

    // 이미지 포함 게시물 등록
    @PostMapping("/{studyId}/posts/with-images")
    @Operation(summary = "스터디 게시물 등록 (이미지 포함)", description = "이미지 파일과 함께 게시물을 등록합니다.")
    public ResponseEntity<String> createPostWithImages(
            @Parameter(description = "스터디 ID", required = true, example = "1")
            @PathVariable int studyId,

            @Parameter(description = "게시물 정보 (JSON)", required = true)
            @RequestPart("post") StudyPostCreateDTO postDto,

            @Parameter(description = "업로드할 이미지 파일들", required = true)
            @RequestPart("images") List<MultipartFile> images,

            @Parameter(description = "작성자 멤버 ID", required = true, example = "1")
            @RequestParam int memberId) {
        return studyPostService.createPost(studyId, memberId, postDto, images);
    }


    // 게시물 수정
    @PutMapping("/posts/{postId}")
    @Operation(summary = "스터디 게시물 수정", description = "게시물 작성자가 자신의 게시물을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시물 수정 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터 또는 존재하지 않는 게시물"),
            @ApiResponse(responseCode = "403", description = "게시물 수정 권한 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    public ResponseEntity<String> updatePost(
            @PathVariable int postId,
            @RequestBody StudyPostUpdateDTO postDto,
            @RequestParam int memberId) {
        return studyPostService.updatePost(postId, memberId, postDto);
    }

    // 게시물 삭제
    @DeleteMapping("/posts/{postId}")
    @Operation(summary = "스터디 게시물 삭제", description = "게시물 작성자가 자신의 게시물을 삭제합니다. (소프트 딜리트)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시물 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 게시물"),
            @ApiResponse(responseCode = "403", description = "게시물 삭제 권한 없음"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    public ResponseEntity<String> deletePost(
            @PathVariable int postId,
            @RequestParam int memberId) {
        return studyPostService.deletePost(postId, memberId);
    }

}
