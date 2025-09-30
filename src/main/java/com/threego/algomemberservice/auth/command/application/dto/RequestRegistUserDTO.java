package com.threego.algomemberservice.auth.command.application.dto;

import lombok.Data;

@Data
public class RequestRegistUserDTO {
    private String email;
    private String password;
    private String nickname;

}
