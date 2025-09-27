package com.threego.algo.member.query.service;

import com.threego.algo.member.query.dao.AuthMapper;
import com.threego.algo.member.query.dto.GetLoginUserResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthQueryServiceImpl implements AuthQueryService {
    private final AuthMapper authMapper;

    public AuthQueryServiceImpl(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    @Override
    public GetLoginUserResponseDTO findMemberByEmail(String email) {
       GetLoginUserResponseDTO dto = authMapper.selectMemberByEmail(email);
       return dto;
    }
}
