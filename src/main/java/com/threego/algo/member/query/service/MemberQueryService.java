package com.threego.algo.member.query.service;

import com.threego.algo.member.query.dto.MemberDetailResponseDTO;

public interface MemberQueryService {
    MemberDetailResponseDTO findMemberById (int id);
}
