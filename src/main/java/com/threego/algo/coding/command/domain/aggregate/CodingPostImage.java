package com.threego.algo.coding.command.domain.aggregate;

import com.threego.algo.common.util.DateTimeUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "Coding_Post_Image")
public class CodingPostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private CodingPost post;

    @Column(name = "image_url", nullable = false, length = 255)
    private String imageUrl;

    public CodingPostImage(CodingPost post, String imageUrl, String createdAt) {
        this.post = post;
        this.imageUrl = imageUrl;
    }

}
