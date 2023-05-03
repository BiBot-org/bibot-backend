package com.coderecipe.v1.limitation.model;

import com.coderecipe.v1.limitation.dto.LimitDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "limitation")
public class Limitation {

    @Id
    @Column(name = "limitation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "rank_id")
    private int rankId;

    @Column(name = "automated_cost")
    private int automatedCost;

    public static Limitation of(LimitDTO dto) {
        return Limitation.builder()
            .id(dto.getId())
            .categoryId(dto.getCategoryId())
            .rankId(dto.getRankId())
            .automatedCost(dto.getAutomatedCost())
            .build();
    }

}
