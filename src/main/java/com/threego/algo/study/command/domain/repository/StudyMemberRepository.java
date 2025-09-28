package com.threego.algo.study.command.domain.repository;

import com.threego.algo.member.command.domain.aggregate.Member;
import com.threego.algo.study.command.domain.aggregate.Study;
import com.threego.algo.study.command.domain.aggregate.StudyMember;
import com.threego.algo.study.command.domain.aggregate.enums.StudyRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudyMemberRepository extends JpaRepository<StudyMember, Integer> {

    /* 설명. 리더 권한 확인 (권한 체크용) */
    boolean existsByStudyIdAndMemberIdAndRole(Integer studyId, Integer memberId, StudyRole role);

    boolean existsByStudyIdAndMemberIdAndRoleIn(Integer studyId, Integer memberId, List<StudyRole> list);

    Optional<Object> findByStudyAndMember(Study study, Member leader);

    long countByStudyAndMemberNot(Study study, Member leader);

    Optional<Object> findByStudyIdAndMemberId(Integer studyId, Integer memberId);
}