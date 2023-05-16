package com.coderecipe.v1.card.model.repository;

import com.coderecipe.v1.card.model.Card;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICardRepository extends JpaRepository<Card, Long> {

    List<Card> getByUserId(UUID userId);

}
