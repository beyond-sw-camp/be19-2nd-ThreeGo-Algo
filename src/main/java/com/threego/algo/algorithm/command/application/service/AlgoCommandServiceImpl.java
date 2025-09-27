package com.threego.algo.algorithm.command.application.service;

import com.threego.algo.algorithm.command.application.dto.*;
import com.threego.algo.algorithm.command.domain.aggregate.*;
import com.threego.algo.algorithm.command.domain.repository.*;
import com.threego.algo.member.command.domain.aggregate.Member;
import com.threego.algo.member.command.domain.repository.MemberCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlgoCommandServiceImpl implements AlgoCommandService {
    private final AlgoRoadmapCommandRepository algoRoadmapCommandRepository;
    private final MemberCommandRepository memberCommandRepository;
    private final AlgoPostCommandRepository algoPostCommandRepository;
    private final AlgoPostImageCommandRepository algoPostImageCommandRepository;
    private final AlgoQuizQuestionCommandRepository algoQuizQuestionCommandRepository;
    private final AlgoQuizOptionCommandRepository algoQuizOptionCommandRepository;

    @Autowired
    public AlgoCommandServiceImpl(AlgoRoadmapCommandRepository algoRoadmapCommandRepository
            , MemberCommandRepository memberCommandRepository
            , AlgoPostCommandRepository algoPostCommandRepository
            , AlgoPostImageCommandRepository algoPostImageCommandRepository
            , AlgoQuizQuestionCommandRepository algoQuizQuestionCommandRepository
            , AlgoQuizOptionCommandRepository algoQuizOptionCommandRepository) {
        this.algoRoadmapCommandRepository = algoRoadmapCommandRepository;
        this.memberCommandRepository = memberCommandRepository;
        this.algoPostCommandRepository = algoPostCommandRepository;
        this.algoPostImageCommandRepository = algoPostImageCommandRepository;
        this.algoQuizQuestionCommandRepository = algoQuizQuestionCommandRepository;
        this.algoQuizOptionCommandRepository = algoQuizOptionCommandRepository;
    }

    @Override
    public AlgoRoadmap createAlgoRoadmap(final AlgoRoadmapRequestDTO request) {
        validAlgoRoadmapTitle(request.getTitle());

        final AlgoRoadmap algoRoadmap = new AlgoRoadmap(request.getTitle(), request.getDescription(),
                request.getOrder());

        return algoRoadmapCommandRepository.save(algoRoadmap);
    }

    @Transactional
    @Override
    public AlgoRoadmap updateAlgoRoadmap(final int roadmapId, final AlgoRoadmapRequestDTO request) throws Exception {
        // TODO 커스텀 예외 발생 및 처리 필요
        final AlgoRoadmap algoRoadmap = findAlgoRoadmapById(roadmapId);

        if (!algoRoadmap.getTitle().equals(request.getTitle())) {
            validAlgoRoadmapTitle(request.getTitle());
        }

        if (!algoRoadmap.getTitle().equals(request.getTitle())
                || !algoRoadmap.getDescription().equals(request.getDescription())
                || algoRoadmap.getOrder() != request.getOrder()) {
            algoRoadmap.updateAlgoRoadmap(request.getTitle(), request.getDescription(), request.getOrder());
        }

        return algoRoadmap;
    }

    @Transactional
    @Override
    public AlgoPostDetailResponseDTO createAlgoPost(final int memberId, final int roadmapId,
                                                    final AlgoPostRequestDTO request) throws Exception {
        final Member member = findMemberById(memberId);

        final AlgoRoadmap algoRoadmap = findAlgoRoadmapById(roadmapId);

        AlgoPost algoPost = new AlgoPost(request.getTitle(), request.getContent(), algoRoadmap, member);

        algoPost = algoPostCommandRepository.save(algoPost);

        final AlgoPostDetailResponseDTO response = AlgoPostDetailResponseDTO.of(algoPost);

        final List<String> savedAlgoPostImages = saveAlgoPostImages(request.getImageUrls(), algoPost).stream()
                .map(AlgoPostImage::getImageUrl)
                .collect(Collectors.toList());

        response.setImageUrls(savedAlgoPostImages);

        final List<AlgoQuizQuestionResponseDTO> questionResponses = new ArrayList<>();

        for (AlgoQuizQuestionRequestDTO quizRequest : request.getQuizzes()) {
            final AlgoQuizQuestion algoQuizQuestion = new AlgoQuizQuestion(quizRequest.getQuestion(), quizRequest.getType()
                    , algoPost);

            final AlgoQuizQuestion savedAlgoQuizQuestion = algoQuizQuestionCommandRepository.save(algoQuizQuestion);

            final List<AlgoQuizOption> options = quizRequest.getOptions().stream()
                    .map((option) -> new AlgoQuizOption(option.getOptionText(), option.isCorrect(), savedAlgoQuizQuestion))
                    .collect(Collectors.toList());

            final List<AlgoQuizOptionResponseDTO> optionResponse = algoQuizOptionCommandRepository.saveAll(options).stream()
                    .map(AlgoQuizOptionResponseDTO::of)
                    .collect(Collectors.toList());

            final AlgoQuizQuestionResponseDTO questionResponse = AlgoQuizQuestionResponseDTO.of(savedAlgoQuizQuestion
                    , optionResponse);

            questionResponses.add(questionResponse);
        }

        response.setQuizzes(questionResponses);

        algoPost.getAlgoRoadmap().updateQuestionCount(algoPost.getAlgoRoadmap().getQuestionCount()
                + questionResponses.size());

        return response;
    }

    private List<AlgoPostImage> saveAlgoPostImages(final List<String> imageUrls, final AlgoPost algoPost) {
        final List<AlgoPostImage> algoPostImages = imageUrls.stream()
                .map((imageUrl) -> new AlgoPostImage(imageUrl, algoPost))
                .collect(Collectors.toList());

        return algoPostImageCommandRepository.saveAll(algoPostImages);
    }

    private void validAlgoRoadmapTitle(final String title) {
        if (algoRoadmapCommandRepository.existsByTitle(title)) {
            throw new RuntimeException("제목을 가진 로드맵이 이미 존재합니다.");
        }
    }

    private AlgoRoadmap findAlgoRoadmapById(final int roadmapId) throws Exception {
        return algoRoadmapCommandRepository.findById(roadmapId).orElseThrow(Exception::new);
    }

    private Member findMemberById(final int memberId) throws Exception {
        return memberCommandRepository.findById(memberId).orElseThrow(Exception::new);
    }
}