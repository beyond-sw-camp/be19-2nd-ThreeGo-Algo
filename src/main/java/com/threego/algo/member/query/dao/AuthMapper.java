package com.threego.algo.member.query.dao;

import com.threego.algo.member.query.dto.GetLoginUserResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthMapper {
    GetLoginUserResponseDTO selectMemberByEmail(
            @Param("email")String email
    );
}
