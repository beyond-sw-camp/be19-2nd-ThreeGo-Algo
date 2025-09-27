package com.threego.algo.member.query.dao;

import com.threego.algo.member.query.dto.GetLoginUserResponseDTO;
import com.threego.algo.member.query.dto.GetMemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    GetMemberDTO selectMemberById(
            @Param("id")String id
    );
}