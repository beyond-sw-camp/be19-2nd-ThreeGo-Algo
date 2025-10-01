package com.threego.algomemberservice.member.query.service;

import com.threego.algomemberservice.member.query.dao.MemberMapper;
import com.threego.algomemberservice.member.query.dto.AdminMemberDetailResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminMemberQueryServiceImpl implements AdminMemberQueryService {
    private final MemberMapper memberMapper;

    public AdminMemberQueryServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public AdminMemberDetailResponseDTO findMemberDetailsById(int id) {
        AdminMemberDetailResponseDTO dto = memberMapper.selectMemberDetailsById(id);
        return dto;
    }

    @Override
    public List<AdminMemberDetailResponseDTO> findMemberList() {
        return memberMapper.selectAllMemberDetails();
    }
}
