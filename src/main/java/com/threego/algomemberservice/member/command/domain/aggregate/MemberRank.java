package com.threego.algomemberservice.member.command.domain.aggregate;

import com.threego.algomemberservice.member.command.domain.aggregate.enums.RankName;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@Table(name = "Member_Rank")
@NoArgsConstructor
@Entity
public class MemberRank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RankName name;

    @Column(name = "min_point", nullable = false)
    private int minPoint;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;


}
