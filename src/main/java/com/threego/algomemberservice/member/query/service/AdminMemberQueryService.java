package com.threego.algomemberservice.member.query.service;


import com.threego.algomemberservice.member.query.dto.AdminMemberDetailResponseDTO;

import java.util.List;

public interface AdminMemberQueryService {
    AdminMemberDetailResponseDTO findMemberDetailsById(int id);

    List<AdminMemberDetailResponseDTO> findMemberList();
}
