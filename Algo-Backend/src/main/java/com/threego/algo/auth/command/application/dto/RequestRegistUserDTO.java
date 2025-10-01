package com.threego.algo.auth.command.application.dto;

import lombok.Data;

@Data
public class RequestRegistUserDTO {
    private String email;
    private String password;
    private String nickname;

}
