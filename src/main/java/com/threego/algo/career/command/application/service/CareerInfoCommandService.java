package com.threego.algo.career.command.application.service;

import com.threego.algo.career.command.application.dto.CareerInfoPostCreateRequest;
import com.threego.algo.career.command.domain.aggregate.CareerInfoPost;
import com.threego.algo.career.command.domain.repository.CareerPostCommandRepository;
import com.threego.algo.member.command.domain.aggregate.Member;
import com.threego.algo.member.command.domain.repository.MemberCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CareerInfoCommandService {
    private final CareerPostCommandRepository careerRepository;
    private final MemberCommandRepository memberRepository;

    @Autowired
    public CareerInfoCommandService(CareerPostCommandRepository careerRepository, MemberCommandRepository memberRepository) {
        this.careerRepository = careerRepository;
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

        return careerRepository.save(post).getId();
    }

    @Transactional
    public void deletePost(Integer postId) {
        CareerInfoPost post = careerRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));

        // TODO: 로그인한 회원 == post.getMember() 확인 (본인 글만 삭제 가능)

        post.delete();
    }
}
