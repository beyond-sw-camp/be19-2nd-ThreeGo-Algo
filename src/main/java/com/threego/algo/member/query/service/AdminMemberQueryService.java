package com.threego.algo.member.query.service;


import com.threego.algo.member.query.dto.AdminMemberDetailResponseDTO;

import java.util.List;

public interface AdminMemberQueryService {
    AdminMemberDetailResponseDTO findMemberDetailsById(int id);

    List<AdminMemberDetailResponseDTO> findMemberList();
}
