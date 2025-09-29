package com.threego.algo.member.command.application.service;

import com.threego.algo.member.command.domain.aggregate.Role;
import com.threego.algo.member.command.domain.aggregate.enums.RoleName;
import com.threego.algo.member.command.domain.aggregate.enums.Status;
import com.threego.algo.member.command.domain.aggregate.Member;
import com.threego.algo.member.command.domain.repository.MemberCommandRepository;
import com.threego.algo.member.command.domain.repository.MemberRoleCommandRepository;
import com.threego.algo.member.command.domain.repository.RoleCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AdminMemberCommandServiceImpl implements AdminMemberCommandService {
    private final MemberCommandRepository memberCommandRepository;
    private final MemberRoleCommandRepository memberRoleCommandRepository;
    private final RoleCommandRepository roleCommandRepository;

    @Autowired
    public AdminMemberCommandServiceImpl(MemberCommandRepository memberCommandRepository,
                                         MemberRoleCommandRepository memberRoleCommandRepository,
                                         RoleCommandRepository roleCommandRepository) {
        this.memberCommandRepository = memberCommandRepository;
        this.memberRoleCommandRepository = memberRoleCommandRepository;
        this.roleCommandRepository = roleCommandRepository;
    }

    @Transactional
    @Override
    public void updateMemberStatus(int memberId, Status status) {
        Member member =  memberCommandRepository.findById(memberId)
                                                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        member.setStatus(status);
        memberCommandRepository.save(member);
    }

    @Transactional
    @Override
    public void updateMemberRole(int memberId, RoleName roleName) {
        Member member = memberCommandRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        Role role = roleCommandRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("해당 역할이 존재하지 않습니다."));

        member.getRoles().clear();
        member.getRoles().add(role);

        memberCommandRepository.save(member);
    }
}
