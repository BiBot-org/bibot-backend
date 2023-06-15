package com.coderecipe.v1.card.service;

import com.coderecipe.v1.card.dto.CardDTO;
import com.coderecipe.v1.card.dto.vo.CardReq.CreateCard;
import com.coderecipe.v1.card.dto.vo.CardRes.CardInfoRes;
import com.coderecipe.v1.payment.dto.vo.PaymentRes.PaymentInfo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ICardService {

    Long addCard(CreateCard req, UUID userId);

    CardDTO getCard(Long cardId);

    List<CardInfoRes> getAllCard(UUID userId);

    Long deleteCard(Long cardId, UUID userId);

    List<PaymentInfo> getPayments(Long cardId, LocalDateTime startDateTime, LocalDateTime endDateTime);

    Integer getAmount(Long cardId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
