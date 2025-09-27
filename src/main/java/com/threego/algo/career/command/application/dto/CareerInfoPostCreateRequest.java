package com.threego.algo.career.command.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CareerInfoPostCreateRequest {
    private String title;
    private String content;
}
