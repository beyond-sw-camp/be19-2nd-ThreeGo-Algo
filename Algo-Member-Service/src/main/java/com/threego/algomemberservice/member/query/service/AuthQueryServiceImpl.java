package com.threego.algomemberservice.member.query.service;

import com.threego.algomemberservice.member.query.dao.AuthMapper;
import com.threego.algomemberservice.member.query.dto.LoginUserResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthQueryServiceImpl implements AuthQueryService {
    private final AuthMapper authMapper;

    public AuthQueryServiceImpl(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    @Override
    public LoginUserResponseDTO findMemberByEmail(String email) {
       LoginUserResponseDTO dto = authMapper.selectMemberByEmail(email);
       return dto;
    }
}
