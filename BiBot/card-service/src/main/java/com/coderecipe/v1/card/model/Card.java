package com.coderecipe.v1.card.model;

import com.coderecipe.v1.card.dto.vo.CardReq.CreateCard;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "card")
public class Card {

    @Id
    @Column(name = "id")
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

    public static Card of(CreateCard res, UUID userId) {
        return Card.builder()
                .userId(userId)
                .cardNo(res.getCardNo())
                .cardCompany(res.getCardCompany())
                .cardCvc(res.getCardCvc())
                .cardValid(res.getCardValid()).build();
    }

}
