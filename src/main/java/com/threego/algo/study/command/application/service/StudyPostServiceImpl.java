package com.threego.algo.study.command.application.service;

import com.threego.algo.common.service.S3Service;
import com.threego.algo.member.command.domain.aggregate.Member;
import com.threego.algo.member.command.domain.aggregate.MemberRole;
import com.threego.algo.member.command.domain.repository.MemberRepository;
import com.threego.algo.member.command.domain.repository.MemberRoleRepository;
import com.threego.algo.study.command.application.dto.create.StudyPostCreateDTO;
import com.threego.algo.study.command.application.dto.create.StudyReportCreateDTO;
import com.threego.algo.study.command.application.dto.update.StudyPostUpdateDTO;
import com.threego.algo.study.command.domain.aggregate.Study;
import com.threego.algo.study.command.domain.aggregate.StudyMember;
import com.threego.algo.study.command.domain.aggregate.StudyPost;
import com.threego.algo.study.command.domain.aggregate.StudyPostImage;
import com.threego.algo.study.command.domain.aggregate.enums.StudyRole;
import com.threego.algo.study.command.domain.repository.StudyMemberRepository;
import com.threego.algo.study.command.domain.repository.StudyPostImageRepository;
import com.threego.algo.study.command.domain.repository.StudyPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudyPostServiceImpl implements StudyPostService {

    private final StudyPostRepository studyPostRepository;
    private final StudyPostImageRepository studyPostImageRepository;
    private final StudyMemberRepository studyMemberRepository;
    private final MemberRoleRepository memberRoleRepository;
    private final S3Service s3Service;

    @Override
    public ResponseEntity<String> createPost(int studyId, int memberId, StudyPostCreateDTO request, List<MultipartFile> images) {
        try {
            // 1. 스터디 멤버 권한 확인
            validateStudyMemberAccess(studyId, memberId);

            // 2. 이미지 업로드 및 저장 (S3 연동 후 구현)
            if (images != null && !images.isEmpty()) {
                for (MultipartFile image : images) {
                    if (!s3Service.isValidImageFile(image)) {
                        return ResponseEntity.badRequest()
                                .body("지원하지 않는 이미지 형식입니다. (JPG, JPEG, PNG, GIF, WEBP만 가능)");
                    }
                    if (!s3Service.isValidFileSize(image)) {
                        return ResponseEntity.badRequest()
                                .body("이미지 파일 크기는 5MB 이하여야 합니다.");
                    }
                }

                if (images.size() > 10) {
                    return ResponseEntity.badRequest()
                            .body("이미지는 최대 10개까지 업로드 가능합니다.");
                }
            }

            // 3. 게시물 생성
            StudyPost post = new StudyPost(studyId, memberId, request.getTitle(), request.getContent());
            studyPostRepository.save(post);

            // 4. 이미지 S3 업로드 및 DB 저장
            if (images != null && !images.isEmpty()) {
                for (MultipartFile image : images) {
                    try {
                        String imageUrl = s3Service.uploadStudyPostImage(image);
                        StudyPostImage postImage = new StudyPostImage(post.getId(), imageUrl);
                        studyPostImageRepository.save(postImage);
                    } catch (Exception e) {
                        // 이미지 업로드 실패 시 이미 업로드된 이미지들 정리
                        rollbackUploadedImages(post.getId());
                        throw new IllegalArgumentException("이미지 업로드 중 오류가 발생했습니다: " + e.getMessage());
                    }
                }
            }

            return ResponseEntity.status(HttpStatus.CREATED).body("게시물이 성공적으로 등록되었습니다.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("게시물 등록 중 오류가 발생했습니다.");
        }
    }


    @Override
    public ResponseEntity<String> updatePost(int postId, int memberId, StudyPostUpdateDTO request) {
        try {
            // 1. 게시물 존재 여부 확인
            StudyPost post = studyPostRepository.findById(postId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));

            // 2. 작성자 권한 확인
            if (post.getMemberId()!=(memberId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("게시물을 수정할 권한이 없습니다.");
            }

            // 3. 게시물 수정
            post.updatePost(request.getTitle(), request.getContent());

            return ResponseEntity.ok("게시물이 성공적으로 수정되었습니다.");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("게시물 수정 중 오류가 발생했습니다.");
        }
    }

    @Override
    public ResponseEntity<String> deletePost(int postId, int memberId) {
        try {
            // 1. 게시물 존재 여부 확인
            StudyPost post = studyPostRepository.findById(postId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));

            // 2. 작성자 권한 확인
            if (post.getMemberId()!=(memberId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("게시물을 삭제할 권한이 없습니다.");
            }

            // 3. 소프트 딜리트
            post.softDelete();

            return ResponseEntity.ok("게시물이 성공적으로 삭제되었습니다.");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("게시물 삭제 중 오류가 발생했습니다.");
        }
    }

    private void rollbackUploadedImages(int postId) {
        try {
            List<StudyPostImage> uploadedImages = studyPostImageRepository.findByPostId((postId));
            for (StudyPostImage image : uploadedImages) {
                try {
                    s3Service.deleteFile(image.getImageUrl());
                } catch (Exception e) {
                    System.err.println("롤백 중 S3 파일 삭제 실패: " + image.getImageUrl());
                }
            }
            studyPostImageRepository.deleteByPostId(postId);
        } catch (Exception e) {
            System.err.println("이미지 롤백 처리 중 오류 발생: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> adminDeletePost(int postId, int adminId) {
        try {
            // 1. 관리자 권한 확인
            if (!isAdmin(adminId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("관리자 권한이 필요합니다.");
            }

            // 2. 스터디 게시물 존재 여부 확인
            StudyPost post = studyPostRepository.findById(postId)
                    .orElseThrow(() -> new IllegalArgumentException("스터디 게시물을 찾을 수 없습니다."));

            // 3. 소프트 딜리트 (VISIBILITY: Y → N)
            post.softDelete();
            studyPostRepository.save(post);

            return ResponseEntity.ok("스터디 게시물이 삭제되었습니다.");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("스터디 게시물 삭제 중 오류가 발생했습니다.");
        }
    }

    private boolean isAdmin(int memberId) {
        try {
            int roleId = memberRoleRepository.getRoleIdByMemberId(memberId);
            return roleId == 2;
        } catch (Exception e) {
            return false;
        }
    }

    private void validateStudyMemberAccess(int studyId, int memberId) {
        StudyMember studyMember = getStudyMember(studyId, memberId);

        if (!studyMember.isActive()) {
            throw new IllegalArgumentException("스터디 접근 권한이 없습니다.");
        }
    }

    private StudyMember getStudyMember(int studyId, int memberId) {
        return (StudyMember) studyMemberRepository.findByStudyIdAndMemberId(studyId, memberId)
                .orElseThrow(() -> new IllegalArgumentException("스터디 멤버가 아닙니다."));
    }

}
