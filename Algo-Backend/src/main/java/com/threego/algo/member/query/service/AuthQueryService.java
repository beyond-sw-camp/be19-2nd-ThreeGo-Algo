package com.threego.algo.member.query.service;

import com.threego.algo.member.query.dto.LoginUserResponseDTO;


public interface AuthQueryService {
    LoginUserResponseDTO findMemberByEmail(String email);
}
