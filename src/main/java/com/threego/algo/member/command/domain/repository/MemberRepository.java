package com.threego.algo.member.command.domain.repository;

import com.threego.algo.member.command.domain.aggregate.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    /*
     * JpaRepository에서 자동 제공되는 메소드들:
     * - Optional<Member> findById(Integer id)
     * - Member save(Member entity)
     * - void delete(Member entity)
     */

    // 추가 필요한 메소드들
    Optional<Member> findByEmail(String email);
    Optional<Member> findByNickname(String nickname);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
}