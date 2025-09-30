package com.threego.algomemberservice.member.query.dao;

import com.threego.algomemberservice.member.query.dto.MemberDetailResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MemberMapper {
    MemberDetailResponseDTO selectMemberById(
            @Param("id") int id
    );
}