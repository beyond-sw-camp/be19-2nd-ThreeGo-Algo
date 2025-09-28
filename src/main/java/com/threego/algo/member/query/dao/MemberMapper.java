package com.threego.algo.member.query.dao;

import com.threego.algo.member.query.dto.AdminMemberDetailResponseDTO;
import com.threego.algo.member.query.dto.MemberDetailResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    MemberDetailResponseDTO selectMemberById(
            @Param("id") int id
    );

    AdminMemberDetailResponseDTO selectMemberDetailsById(
            @Param("id") int id
    );

    List<AdminMemberDetailResponseDTO> selectAllMemberDetails();
}