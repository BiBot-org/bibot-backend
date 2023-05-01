package com.codeRecipe.payment_history.card.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "card_no")
    private String cardNo;

    @Column(name = "card_company")
    private String cardCompany;

    @Column(name = "card_valid")
    private String cardValid;


}
