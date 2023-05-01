package com.codeRecipe.payment_history.card.repository;

import com.codeRecipe.payment_history.card.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICardRepository extends JpaRepository <Card, Long> {
}
