package com.coderecipe.v1.card.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.v1.card.dto.CardDTO;
import com.coderecipe.v1.card.dto.vo.CardReq.CreateCard;
import com.coderecipe.v1.card.dto.vo.CardRes.CardInfoRes;
import com.coderecipe.v1.card.model.Card;
import com.coderecipe.v1.card.model.repository.ICardRepository;
import com.coderecipe.v1.card.service.ICardService;
import com.coderecipe.v1.payment.dto.vo.PaymentRes.PaymentInfo;
import com.coderecipe.v1.payment.model.repository.IPaymentHistoryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
@CacheConfig(cacheNames = "card")
public class CardServiceImpl implements ICardService {

    private final ICardRepository iCardRepository;
    private final IPaymentHistoryRepository iPaymentHistoryRepository;

    @Override
    public Long addCard(CreateCard req, UUID userId) {
        Card card = Card.of(req, userId);
        iCardRepository.save(card);
        return card.getId();
    }

    @Override
    @Cacheable(key = "#cardId", unless = "#result == null")
    public CardDTO getCard(Long cardId) {
        Card card = iCardRepository.findById(cardId)
                .orElseThrow(() -> new CustomException(ResCode.CARD_NOT_FOUND));
        return CardDTO.of(card);
    }

    @Override
    public List<CardInfoRes> getAllCard(UUID userId) {
        return iCardRepository.findCardsByUserIdOrderById(userId)
                .stream().map(CardInfoRes::of).toList();
    }

    @Override
    public Long deleteCard(Long cardId, UUID userId) {
        Card card = iCardRepository.findById(cardId)
                .orElseThrow(() -> new CustomException(ResCode.CARD_NOT_FOUND));
        if (!card.getUserId().equals(userId)) {
            throw new CustomException(ResCode.BAD_REQUEST);
        } else {
            iCardRepository.deleteById(cardId);
            return cardId;
        }
    }

    @Override
    public List<PaymentInfo> getPayments(Long cardId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return iPaymentHistoryRepository.findAllByRegTimeBetweenAndCardIdOrderByRegTimeDesc(
                        startDateTime, endDateTime, cardId).stream()
                .map(PaymentInfo::of).toList();
    }


    @Override
    public Integer getAmount(Long cardId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return iPaymentHistoryRepository.findAndSumAllByRegTimeBetweenAndCardId(
                startDateTime, endDateTime,
                iCardRepository.getReferenceById(cardId));
    }

}
