package com.coderecipe.v1.card.model;

import com.coderecipe.global.utils.ModelMapperUtils;
import com.coderecipe.v1.card.dto.vo.CardReq.*;
import jakarta.persistence.*;

import java.util.UUID;

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

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "card_no", columnDefinition = "VARCHAR(30) NOT NULL")
    private String cardNo;

    @Column(name = "card_company", columnDefinition = "VARCHAR(20) NOT NULL")
    private String cardCompany;

    @Column(name = "card_cvc", columnDefinition = "VARCHAR(10) NOT NULL")
    private String cardCvc;

    @Column(name = "card_valid", columnDefinition = "VARCHAR(20) NOT NULL")
    private String cardValid;

    public static Card of(CreateCard res) {
        return ModelMapperUtils.getModelMapper().map(res, Card.class);
    }

}
