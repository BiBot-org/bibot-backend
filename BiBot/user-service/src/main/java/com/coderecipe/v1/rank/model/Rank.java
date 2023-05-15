package com.coderecipe.v1.rank.model;

import com.coderecipe.v1.rank.dto.RankDTO;
import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "rank")
@Builder
public class Rank {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(20) NOT NULL")
    private String name;

    public static Rank of(RankDTO dto) {
        return Rank.builder()
            .id(dto.getId())
            .name(dto.getName())
            .build();
    }
}
