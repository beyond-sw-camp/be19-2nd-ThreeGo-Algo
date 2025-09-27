package com.threego.algo.member.query.service;

import com.threego.algo.member.query.dto.GetLoginUserResponseDTO;


public interface AuthQueryService {
    GetLoginUserResponseDTO findMemberByEmail(String email);
}
