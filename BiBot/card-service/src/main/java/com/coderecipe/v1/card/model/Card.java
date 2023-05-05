package com.coderecipe.v1.card.model;

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
@Table(name = "Card")
public class Card {
    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "card_no")
    private String cardNo;

    @Column(name = "card_company")
    private String cardCompany;

    @Column(name = "card_valid")
    private String cardValid;

    public static Card of(CardDTO dto) {
        return Card.builder()
                .id(dto.getId())
                .cardNo(dto.getCardNo())
                .cardCompany(dto.getCardCompany())
                .cardValid(dto.getCardValid())
                .build();
    }

}
