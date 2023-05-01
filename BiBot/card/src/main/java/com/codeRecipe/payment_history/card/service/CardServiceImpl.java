package com.codeRecipe.payment_history.card.service;

import com.codeRecipe.payment_history.card.model.Card;
import com.codeRecipe.payment_history.card.model.dto.RequestCard;
import com.codeRecipe.payment_history.card.repository.ICardRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
public class CardServiceImpl implements ICardService{
    private final ICardRepository iCardRepository;

    @Override
    public void addCard(RequestCard requestCard) {
        Card card = Card.builder()
                .cardNo(requestCard.getCardNo()).cardCompany(requestCard.getCardCompany())
                .cardValid(requestCard.getCardValid()).build();
        iCardRepository.save(card);
    }
}
