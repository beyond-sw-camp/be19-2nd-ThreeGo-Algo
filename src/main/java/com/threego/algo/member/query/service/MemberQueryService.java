package com.threego.algo.member.query.service;

import com.threego.algo.member.query.dto.GetLoginUserResponseDTO;
import com.threego.algo.member.query.dto.GetMemberDTO;

public interface MemberQueryService {
    GetMemberDTO findMemberById (String id);
}
