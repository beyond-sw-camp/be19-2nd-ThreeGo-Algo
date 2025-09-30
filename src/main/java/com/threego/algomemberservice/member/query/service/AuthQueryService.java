package com.threego.algomemberservice.member.query.service;

import com.threego.algomemberservice.member.query.dto.LoginUserResponseDTO;


public interface AuthQueryService {
    LoginUserResponseDTO findMemberByEmail(String email);
}
