package com.threego.algo.study.command.domain.aggregate;

import com.threego.algo.common.util.DateTimeUtils;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Study")
@Getter
@Setter
@NoArgsConstructor
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "post_id", nullable = false)
    private int recruitPostId; // 원본 모집글 ID

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(name = "start_date", nullable = false)
    private String startDate;

    @Column(name = "end_date") // NULL 허용
    private String endDate;



    // 생성자
    public Study(int recruitPostId, String name, String description,
                 String startDate, String endDate) {
        this.recruitPostId = recruitPostId;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    // 비즈니스 메소드
    public void updateStudy(String name, String description, String endDate) {
        this.name = name;
        this.description = description;
        this.endDate = endDate;
    }


}