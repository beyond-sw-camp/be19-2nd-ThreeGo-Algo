package com.threego.algo.study.query.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyPostDetailDTO {
    private int id;
    private int studyId;
    private int memberId;
    private String memberNickname;
    private String title;
    private String content;
    private int commentCount;
    private String createdAt;
    private String updatedAt;
    private String visibility;
    private List<PostImageInfo> images;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostImageInfo {
        private int imageId;
        private String imageUrl;
        private String createdAt;
    }
}