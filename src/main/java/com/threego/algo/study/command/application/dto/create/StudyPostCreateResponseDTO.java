package com.threego.algo.study.command.application.dto.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StudyPostCreateResponseDTO {
    private int postId;
    private String title;
    private String content;
    private List<String> imageUrls;
    private String message;
}
