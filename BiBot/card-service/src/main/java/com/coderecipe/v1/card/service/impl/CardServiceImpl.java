package com.coderecipe.v1.card.service.impl;

import com.coderecipe.v1.card.dto.CardDTO;
import com.coderecipe.v1.card.model.Card;
import com.coderecipe.v1.card.model.repository.ICardRepository;
import com.coderecipe.v1.card.service.ICardService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
public class CardServiceImpl implements ICardService {
    private final ICardRepository iCardRepository;

    @Override
    public List<Long> addCard(List<CardDTO> req) {
        return req.stream().map(e -> {
            Card card = Card.of(e);
            iCardRepository.save(card);
            return card.getId();
        }).toList();
    }

    @Override
    public CardDTO getCard(Long cardId) {
        return new CardDTO(
                1L, "1234-1234-1234-****", "국민카드", "사용가능"
        );
    }
}
