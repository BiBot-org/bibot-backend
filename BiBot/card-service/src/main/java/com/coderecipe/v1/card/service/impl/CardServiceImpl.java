package com.coderecipe.v1.card.service.impl;

import com.coderecipe.global.constant.enums.ResCode;
import com.coderecipe.global.constant.error.CustomException;
import com.coderecipe.v1.card.dto.CardDTO;
import com.coderecipe.v1.card.dto.vo.CardReq.*;
import com.coderecipe.v1.card.dto.vo.CardRes.*;
import com.coderecipe.v1.card.model.Card;
import com.coderecipe.v1.card.model.repository.ICardRepository;
import com.coderecipe.v1.card.service.ICardService;
import com.coderecipe.v1.payment.dto.vo.PaymentRes.*;

import com.coderecipe.v1.payment.model.repository.IPaymentHistoryRepository;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
@CacheConfig(cacheNames = "card")
public class CardServiceImpl implements ICardService {

    private final ICardRepository iCardRepository;
    private final IPaymentHistoryRepository iPaymentHistoryRepository;

    @Override
    @CachePut(key = "#cardId")
    @CacheEvict(key = "#userId")
    public Long addCard(CreateCard req, UUID userId) {
        Card card = Card.of(req, userId);
        iCardRepository.save(card);
        return card.getId();
    }

    @Override
    @Cacheable(key = "#cardId")
    public CardDTO getCard(Long cardId) {
        Card card = iCardRepository.findById(cardId)
            .orElseThrow(() -> new CustomException(ResCode.CARD_NOT_FOUND));
        return CardDTO.of(card);
    }

    @Override
    @Cacheable(key = "#userId")
    public List<CardInfoRes> getAllCard(UUID userId) {
        return iCardRepository.findCardsByUserIdOrderById(userId)
                .stream().map(CardInfoRes::of).toList();
    }

    @Override
    @Caching(evict = {
        @CacheEvict(key = "#cardId"),
        @CacheEvict(key = "#userId"),
    })
    public Long deleteCard(Long cardId, UUID userId) {
        Card card = iCardRepository.findById(cardId)
                .orElseThrow(() -> new CustomException(ResCode.CARD_NOT_FOUND));
        if (card.getUserId() != userId) {
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
