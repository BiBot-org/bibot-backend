package com.coderecipe.v1.user.rank.model;

import com.coderecipe.v1.user.rank.dto.RankDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public void updateRank(RankDTO dto) {
        this.name = dto.getName();
    }
}
