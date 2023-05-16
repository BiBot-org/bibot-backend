package com.coderecipe.v1.card.service;

import com.coderecipe.v1.card.dto.AllCardDTO;
import com.coderecipe.v1.card.dto.CardDTO;
import com.coderecipe.v1.card.dto.vo.CardReq.CardId;
import com.coderecipe.v1.payment.dto.PaymentHistoryDTO;
import java.util.List;
import java.util.UUID;

public interface ICardService {

    Long addCard(CardDTO req);

    CardDTO getCard(Long cardId);

    List<AllCardDTO> getAllCard(UUID userId);

    String deleteCard(Long cardId);

    List<PaymentHistoryDTO> getPayments(Long cardId);
}
