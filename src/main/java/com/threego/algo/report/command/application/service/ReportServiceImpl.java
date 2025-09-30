package com.threego.algo.report.command.application.service;

import com.threego.algo.common.util.DateTimeUtils;
import com.threego.algo.member.command.domain.aggregate.Member;
import com.threego.algo.member.command.domain.repository.MemberRepository;
import com.threego.algo.report.command.application.dto.ReportRequest;
import com.threego.algo.report.command.domain.aggregate.Report;
import com.threego.algo.report.command.domain.aggregate.ReportCategory;
import com.threego.algo.report.command.domain.aggregate.ReportType;
import com.threego.algo.report.command.domain.repository.ReportCategoryRepository;
import com.threego.algo.report.command.domain.repository.ReportRepository;
import com.threego.algo.report.command.domain.repository.ReportTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ReportCategoryRepository categoryRepository;
    private final ReportTypeRepository typeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public void createReport(ReportRequest request) {
        ReportCategory category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));

        ReportType type = typeRepository.findById(request.getTypeId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 신고 타입입니다."));

        Member reporter = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 신고자입니다."));

        Member reportedMember = memberRepository.findById(request.getReportedMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 피신고자입니다."));

        // 중복 신고 방지
        boolean exists = reportRepository.existsByMemberAndTargetIdAndTypeAndReportedMember(
                reporter,
                request.getTargetId(),
                type,
                reportedMember
        );
        if (exists) {
            throw new IllegalArgumentException("이미 신고한 게시물/댓글입니다.");
        }

        Report report = Report.create(
                reporter,
                category,
                type,
                request.getTargetId(),
                reportedMember,
                request.getContent(),
                DateTimeUtils.nowDateTime()
        );

        reportRepository.save(report);
    }
}

