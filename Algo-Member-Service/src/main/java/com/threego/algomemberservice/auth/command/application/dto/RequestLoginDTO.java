package com.threego.algomemberservice.auth.command.application.dto;

import lombok.Data;

@Data
public class RequestLoginDTO {
    private String email;
    private String password;
}
