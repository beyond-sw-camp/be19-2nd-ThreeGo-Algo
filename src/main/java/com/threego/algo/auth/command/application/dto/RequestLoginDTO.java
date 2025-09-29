package com.threego.algo.auth.command.application.dto;

import lombok.Data;

@Data
public class RequestLoginDTO {
    private String email;
    private String password;
}
