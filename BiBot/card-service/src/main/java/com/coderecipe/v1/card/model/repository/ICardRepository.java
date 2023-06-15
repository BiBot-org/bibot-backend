package com.coderecipe.v1.card.model.repository;

import com.coderecipe.v1.card.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ICardRepository extends JpaRepository<Card, Long> {

    List<Card> findCardsByUserIdOrderById(UUID userId);
}
