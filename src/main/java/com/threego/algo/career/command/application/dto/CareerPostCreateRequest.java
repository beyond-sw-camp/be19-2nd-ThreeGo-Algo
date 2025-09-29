package com.threego.algo.career.command.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CareerPostCreateRequest {
    private String title;
    private String content;
    private String imageUrl;
}
