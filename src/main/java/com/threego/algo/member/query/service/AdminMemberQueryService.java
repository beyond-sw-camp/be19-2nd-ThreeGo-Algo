package com.threego.algo.member.query.service;


import com.threego.algo.member.query.dto.AdminMemberDetailResponseDTO;

public interface AdminMemberQueryService {
    AdminMemberDetailResponseDTO findMemberDetailsById(String id);
}
