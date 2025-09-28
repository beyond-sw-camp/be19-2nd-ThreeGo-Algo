package com.threego.algo.auth.command.application.dto;

import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String email;
    private String password;
    private String nickname;
}


