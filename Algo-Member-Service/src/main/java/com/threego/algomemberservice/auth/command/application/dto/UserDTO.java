package com.threego.algomemberservice.auth.command.application.dto;

import com.threego.algomemberservice.member.command.domain.aggregate.MemberRank;
import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String email;
    private String password;
    private String nickname;
    private MemberRank memberRank;
}


