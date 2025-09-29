package com.threego.algo.member.query.service;

import com.threego.algo.member.query.dao.MemberMapper;
import com.threego.algo.member.query.dto.MemberDetailResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberMapper memberMapper;

    public MemberQueryServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }


    @Override
    public MemberDetailResponseDTO findMemberById(int id) {
        MemberDetailResponseDTO dto = memberMapper.selectMemberById(id);
        return dto;
    }
}
