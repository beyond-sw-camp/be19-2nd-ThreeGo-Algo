package com.threego.algo.member.query.service;

import com.threego.algo.member.query.dao.MemberMapper;
import com.threego.algo.member.query.dto.AdminMemberDetailResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class AdminMemberQueryServiceImpl implements AdminMemberQueryService {
    private final MemberMapper memberMapper;

    public AdminMemberQueryServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public AdminMemberDetailResponseDTO findMemberDetailsById(String id) {
        AdminMemberDetailResponseDTO dto = memberMapper.selectMemberDetailsById(id);
        return dto;
    }
}
