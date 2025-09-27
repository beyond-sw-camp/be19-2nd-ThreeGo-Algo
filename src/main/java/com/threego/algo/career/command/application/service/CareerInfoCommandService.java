package com.threego.algo.career.command.application.service;

import com.threego.algo.career.command.application.dto.CareerInfoCommentCommandRequest;
import com.threego.algo.career.command.application.dto.CareerInfoPostCreateRequest;
import com.threego.algo.career.command.domain.aggregate.CareerInfoComment;
import com.threego.algo.career.command.domain.aggregate.CareerInfoPost;
import com.threego.algo.career.command.domain.repository.CareerCommentRepository;
import com.threego.algo.career.command.domain.repository.CareerPostRepository;
import com.threego.algo.member.command.domain.aggregate.Member;
import com.threego.algo.member.command.domain.repository.MemberCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CareerInfoCommandService {
    private final CareerPostRepository careerPostRepository;
    private final CareerCommentRepository careerCommentRepository;
    private final MemberCommandRepository memberRepository;

    @Autowired
    public CareerInfoCommandService(CareerPostRepository careerPostRepository, CareerCommentRepository careerCommentRepository, MemberCommandRepository memberRepository) {
        this.careerPostRepository = careerPostRepository;
        this.careerCommentRepository = careerCommentRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Integer createPost(CareerInfoPostCreateRequest request) {
        // TODO: 로그인 회원 정보 가져오기 (Spring Security에서 인증 객체 활용)
        Member member = memberRepository.findById(1)
                .orElseThrow(() -> new IllegalArgumentException("테스트용 회원이 없습니다."));

        CareerInfoPost post = new CareerInfoPost(
                member,
                request.getTitle(),
                request.getContent()
        );

        return careerPostRepository.save(post).getId();
    }

    @Transactional
    public void deletePost(Integer postId) {
        CareerInfoPost post = careerPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));

        // TODO: 로그인한 회원 == post.getMember() 확인 (본인 글만 삭제 가능)

        post.delete();
    }

    @Transactional
    public Integer createComment(Integer postId,
                                 Integer parentId,
                                 CareerInfoCommentCommandRequest request
    ) {
        CareerInfoPost post = careerPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));

        // TODO: 로그인 회원 정보 가져오기 (Spring Security에서 인증 객체 활용)
        Member member = memberRepository.findById(1)
                .orElseThrow(() -> new IllegalArgumentException("테스트용 회원이 없습니다."));

        CareerInfoComment parent = null;
        if(parentId != null) {
            parent = careerCommentRepository.findById(parentId)
                    .orElseThrow(() -> new IllegalArgumentException("부모 댓글이 존재하지 않습니다."));
        }

        CareerInfoComment comment = new CareerInfoComment(
                parent,
                post,
                member,
                request.getContent()
        );
        careerCommentRepository.save(comment);
        post.increaseCommentCount();

        return comment.getId();
    }

    @Transactional
    public void updateComment(Integer commentId, CareerInfoCommentCommandRequest request) {
        CareerInfoComment comment = careerCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        // TODO: 로그인한 회원 == post.getMember() 확인 (본인 댓글만 수정 가능)
        comment.updateComment(request.getContent());
    }
}
