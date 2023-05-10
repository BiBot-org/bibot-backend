package com.coderecipe.v1.card.model.repository;

import com.coderecipe.v1.card.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICardRepository extends JpaRepository<Card, Long> {

}
