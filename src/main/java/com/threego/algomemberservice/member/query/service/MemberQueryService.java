package com.threego.algomemberservice.member.query.service;

import com.threego.algomemberservice.member.query.dto.MemberDetailResponseDTO;

public interface MemberQueryService {
    MemberDetailResponseDTO findMemberById (int id);
}
