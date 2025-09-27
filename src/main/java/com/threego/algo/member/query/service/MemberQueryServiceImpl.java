package com.threego.algo.member.query.service;

import com.threego.algo.member.query.dao.MemberMapper;
import com.threego.algo.member.query.dto.GetLoginUserResponseDTO;
import com.threego.algo.member.query.dto.GetMemberDTO;
import org.springframework.stereotype.Service;

@Service
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberMapper memberMapper;

    public MemberQueryServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }


    @Override
    public GetMemberDTO findMemberById(String id) {
        GetMemberDTO dto = memberMapper.selectMemberById(id);
        return dto;
    }
}
