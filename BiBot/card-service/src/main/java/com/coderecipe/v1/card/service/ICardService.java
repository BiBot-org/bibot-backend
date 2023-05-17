package com.coderecipe.v1.card.service;

import com.coderecipe.v1.card.dto.CardDTO;
import com.coderecipe.v1.card.dto.vo.CardReq.*;
import com.coderecipe.v1.card.dto.vo.CardRes.*;
import com.coderecipe.v1.payment.dto.vo.PaymentRes.*;

import java.util.List;
import java.util.UUID;

public interface ICardService {

    Long addCard(CreateCard req);

    CardDTO getCard(Long cardId);

    List<CardInfoRes> getAllCard(UUID userId);

    Long deleteCard(Long cardId);

    List<PaymentInfo> getPayments(Long cardId);
}
