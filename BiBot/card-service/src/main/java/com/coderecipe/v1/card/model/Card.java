package com.coderecipe.v1.card.model;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.v1.card.dto.CardDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "card")
public class Card {
    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_no", columnDefinition = "VARCHAR(30) NOT NULL")
    private String cardNo;

    @Column(name = "card_company", columnDefinition = "VARCHAR(20) NOT NULL")
    private String cardCompany;

    @Column(name = "card_valid", columnDefinition = "VARCHAR(20) NOT NULL")
    private String cardValid;

    public static Card of(CardDTO dto) {
        return ModelMapperUtils.getModelMapper().map(dto, Card.class);
    }

}
