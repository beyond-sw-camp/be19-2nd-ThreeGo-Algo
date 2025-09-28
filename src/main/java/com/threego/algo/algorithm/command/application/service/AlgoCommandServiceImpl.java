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
    private final AlgoCommentRepository algoCommentRepository;
    private final MemberAlgoCorrectQuizHistoryCommandRepository memberAlgoCorrectQuizHistoryCommandRepository;

    @Autowired
    public AlgoCommandServiceImpl(AlgoRoadmapCommandRepository algoRoadmapCommandRepository
            , MemberCommandRepository memberCommandRepository
            , AlgoPostCommandRepository algoPostCommandRepository
            , AlgoPostImageCommandRepository algoPostImageCommandRepository
            , AlgoQuizQuestionCommandRepository algoQuizQuestionCommandRepository
            , AlgoQuizOptionCommandRepository algoQuizOptionCommandRepository
            , AlgoCommentRepository algoCommentRepository
            , MemberAlgoCorrectQuizHistoryCommandRepository memberAlgoCorrectQuizHistoryCommandRepository) {
        this.algoRoadmapCommandRepository = algoRoadmapCommandRepository;
        this.memberCommandRepository = memberCommandRepository;
        this.algoPostCommandRepository = algoPostCommandRepository;
        this.algoPostImageCommandRepository = algoPostImageCommandRepository;
        this.algoQuizQuestionCommandRepository = algoQuizQuestionCommandRepository;
        this.algoQuizOptionCommandRepository = algoQuizOptionCommandRepository;
        this.algoCommentRepository = algoCommentRepository;
        this.memberAlgoCorrectQuizHistoryCommandRepository = memberAlgoCorrectQuizHistoryCommandRepository;
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

        return response;
    }

    @Transactional
    @Override
    public void deleteAlgoPost(final int postId) throws Exception {
        final AlgoPost algoPost = findAlgoPostById(postId);

        // TODO 주석 제거 필요
        // 삭제할 게시물에 속한 문제 수 카운팅
        final int postQuizCount = algoQuizQuestionCommandRepository.countByAlgoPost(algoPost);

        // 삭제할 게시물이 속한 로드맵의 문제 수 가져오기
        final int totalQuizCount = algoPost.getAlgoRoadmap().getQuestionCount();

        // 게시물 상태 변경 (Y -> N)
        algoPost.updateVisibility();

        // 로드맵의 문제 수 갱신
        algoPost.getAlgoRoadmap().updateQuestionCount(totalQuizCount - postQuizCount);
    }

    @Transactional
    @Override
    public void createComment(final int memberId, final int postId, final AlgoCommentRequestDTO request) throws Exception {
        final Member member = findMemberById(memberId);

        final AlgoPost algoPost = findAlgoPostById(postId);

        AlgoComment parent = null;

        if (request.getParentId() != null) {
            parent = findAlgoCommentId(request.getParentId());
        }

        final AlgoComment comment = new AlgoComment(request.getContent(), member, algoPost, parent);

        algoCommentRepository.save(comment);

        algoPost.updateCommentCount(algoPost.getCommentCount() + 1);
    }

    @Transactional
    @Override
    public void updateComment(final int memberId, final int commentId, final AlgoCommentRequestDTO request) throws Exception {
        final AlgoComment comment = findAlgoCommentId(commentId);

        final Member member = findMemberById(memberId);

        validAuthor(comment.getMember(), member);

        comment.updateContent(request.getContent());
    }

    @Transactional
    @Override
    public void deleteComment(final int memberId, final int commentId) throws Exception {
        final AlgoComment comment = findAlgoCommentId(commentId);

        final Member member = findMemberById(memberId);

        validAuthor(comment.getMember(), member);

        comment.updateVisibility();

        final AlgoPost algoPost = comment.getAlgoPost();

        algoPost.updateCommentCount(algoPost.getCommentCount() - 1);
    }

    @Transactional
    @Override
    public void createCorrectQuizHistory(int memberId, int questionId) throws Exception {
        final Member member = findMemberById(memberId);

        final AlgoQuizQuestion quizQuestion = findAlgoQuizQuestion(questionId);

        final MemberAlgoCorrectQuizHistoryId id = new MemberAlgoCorrectQuizHistoryId(member, quizQuestion);

        if (!memberAlgoCorrectQuizHistoryCommandRepository.existsById(id)) {
            memberAlgoCorrectQuizHistoryCommandRepository.save(new MemberAlgoCorrectQuizHistory(id));
        } else {
            throw new Exception("이미 등록된 퀴즈 정답 제출 이력입니다.");
        }
    }

    @Transactional
    @Override
    public AlgoQuizQuestionResponseDTO createAlgoQuiz(int postId, AlgoQuizQuestionRequestDTO request) throws Exception {
        final AlgoPost algoPost = findAlgoPostById(postId);

        validQuizQuestion(request.getQuestion());

        final AlgoQuizQuestion algoQuizQuestion = new AlgoQuizQuestion(request.getQuestion(), request.getType(), algoPost);

        final AlgoQuizQuestion savedAlgoQuizQuestion = algoQuizQuestionCommandRepository.save(algoQuizQuestion);

        final List<AlgoQuizOption> options = request.getOptions().stream()
                .map((option) -> new AlgoQuizOption(option.getOptionText(), option.isCorrect(), savedAlgoQuizQuestion))
                .collect(Collectors.toList());

        final List<AlgoQuizOptionResponseDTO> optionResponse = algoQuizOptionCommandRepository.saveAll(options).stream()
                .map(AlgoQuizOptionResponseDTO::of)
                .collect(Collectors.toList());

        algoPost.getAlgoRoadmap().updateQuestionCount(algoPost.getAlgoRoadmap().getQuestionCount() + 1);

        return AlgoQuizQuestionResponseDTO.of(savedAlgoQuizQuestion, optionResponse);
    }

    @Transactional
    @Override
    public AlgoQuizQuestionResponseDTO updateAlgoQuiz(final int quizQuestionId,
                                                      final UpdateAlgoQuizQuestionRequestDTO request) throws Exception {
        final AlgoQuizQuestion quizQuestion = findAlgoQuizQuestion(quizQuestionId);

        if (!quizQuestion.getQuestion().equals(request.getQuestion())) {
            validQuizQuestion(request.getQuestion());
            quizQuestion.updateQuestion(request.getQuestion());
        }

        final List<AlgoQuizOption> algoQuizOptions = algoQuizOptionCommandRepository.findByAlgoQuizQuestion(quizQuestion);
        final List<AlgoQuizOptionResponseDTO> optionResponse = new ArrayList<>();

        for (UpdateAlgoQuizOptionRequestDTO dto : request.getOptions()) {
            final AlgoQuizOption algoQuizOption = algoQuizOptions.stream()
                    .filter((option) -> option.getId() == dto.getOptionId())
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("존재하지 않는 보기입니다."));

            algoQuizOption.updateAlgoQuizOption(dto.getOptionText(), dto.isCorrect());

            optionResponse.add(AlgoQuizOptionResponseDTO.of(algoQuizOption));
        }

        return AlgoQuizQuestionResponseDTO.of(quizQuestion, optionResponse);
    }

    private void validQuizQuestion(final String question) {
        if (algoQuizQuestionCommandRepository.existsByQuestionLike(question)) {
            throw new RuntimeException("이미 동일한 내용의 퀴즈 질문이 존재합니다.");
        }
    }

    private AlgoQuizQuestion findAlgoQuizQuestion(final int questionId) throws Exception {
        return algoQuizQuestionCommandRepository.findById(questionId).orElseThrow(Exception::new);
    }

    private void validAuthor(final Member author, final Member loginMember) throws Exception {
        if (author != loginMember) {
            // TODO 커스텀 예외 발생
            throw new Exception("작성자가 아니므로 수정 및 삭제 권한이 없습니다.");
        }
    }

    private AlgoComment findAlgoCommentId(final int parentId) throws Exception {
        return algoCommentRepository.findById(parentId).orElseThrow(Exception::new);
    }

    private List<AlgoPostImage> saveAlgoPostImages(final List<String> imageUrls, final AlgoPost algoPost) {
        final List<AlgoPostImage> algoPostImages = imageUrls.stream()
                .map((imageUrl) -> new AlgoPostImage(imageUrl, algoPost))
                .collect(Collectors.toList());

        return algoPostImageCommandRepository.saveAll(algoPostImages);
    }

    private void validAlgoRoadmapTitle(final String title) {
        if (algoRoadmapCommandRepository.existsByTitle(title)) {
            throw new RuntimeException("이미 존재하는 로드맵 제목입니다.");
        }
    }

    private AlgoRoadmap findAlgoRoadmapById(final int roadmapId) throws Exception {
        return algoRoadmapCommandRepository.findById(roadmapId).orElseThrow(Exception::new);
    }

    private Member findMemberById(final int memberId) throws Exception {
        return memberCommandRepository.findById(memberId).orElseThrow(Exception::new);
    }

    private AlgoPost findAlgoPostById(final int postId) throws Exception {
        return algoPostCommandRepository.findById(postId).orElseThrow(Exception::new);
    }
}