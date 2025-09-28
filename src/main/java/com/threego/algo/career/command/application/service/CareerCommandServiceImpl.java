package com.threego.algo.career.command.application.service;

import com.threego.algo.career.command.application.dto.CareerCommentRequest;
import com.threego.algo.career.command.application.dto.CareerPostCreateRequest;
import com.threego.algo.career.command.domain.aggregate.CareerInfoComment;
import com.threego.algo.career.command.domain.aggregate.CareerInfoPost;
import com.threego.algo.career.command.domain.repository.CareerCommentRepository;
import com.threego.algo.career.command.domain.repository.CareerPostRepository;
import com.threego.algo.member.command.domain.aggregate.Member;
import com.threego.algo.member.command.domain.repository.MemberCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CareerCommandServiceImpl implements CareerCommandService{
    private final CareerPostRepository careerPostRepository;
    private final CareerCommentRepository careerCommentRepository;
    private final MemberCommandRepository memberRepository;

    @Autowired
    public CareerCommandServiceImpl(CareerPostRepository careerPostRepository, CareerCommentRepository careerCommentRepository, MemberCommandRepository memberRepository) {
        this.careerPostRepository = careerPostRepository;
        this.careerCommentRepository = careerCommentRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Integer createPost(CareerPostCreateRequest request) {
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
    public void deletePost(int postId) {
        CareerInfoPost post = careerPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));

        // TODO: 로그인한 회원 == post.getMember() 확인 (본인 글만 삭제 가능)

        if ("N".equals(post.getVisibility())) {
            throw new IllegalStateException("이미 삭제된 게시물입니다.");
        }

        post.delete();
    }

    @Transactional
    public Integer createComment(int postId,
                                 Integer parentId,
                                 CareerCommentRequest request
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
    public void updateComment(int commentId, CareerCommentRequest request) {
        CareerInfoComment comment = careerCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        // TODO: 로그인 회원 == 작성자 확인
        comment.updateComment(request.getContent());
    }

    @Transactional
    public void deleteComment(int commentId) {
        CareerInfoComment comment = careerCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        // TODO: 로그인 회원 == 작성자 확인

        if ("N".equals(comment.getVisibility())) {
            throw new IllegalStateException("이미 삭제된 댓글입니다.");
        }

        comment.deleteComment();
        comment.getPost().decreaseCommentCount();
    }
}
