package com.coderecipe.v1.card.service.impl;

import com.coderecipe.v1.card.dto.AllCardDTO;
import com.coderecipe.v1.card.dto.CardDTO;
import com.coderecipe.v1.card.dto.vo.CardReq.CardId;
import com.coderecipe.v1.card.model.Card;
import com.coderecipe.v1.card.model.repository.ICardRepository;
import com.coderecipe.v1.card.service.ICardService;
import com.coderecipe.v1.payment.dto.PaymentHistoryDTO;
import com.coderecipe.v1.payment.model.PaymentHistory;
import com.coderecipe.v1.payment.model.repository.IPaymentHistoryRepository;
import java.util.UUID;
import java.util.stream.Collectors;
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
    private final IPaymentHistoryRepository iPaymentHistoryRepository;

    @Override
    public Long addCard(CardDTO req) {
        return (iCardRepository.save(Card.of(req))).getId();
    }

    @Override
    public CardDTO getCard(Long cardId) {
        return CardDTO.of(iCardRepository.findById(cardId).get());
    }

    @Override
    public List<AllCardDTO> getAllCard(UUID userId) {
        List<Card> cards = iCardRepository.getByUserId(userId);
        return cards.stream().map(card -> {
                AllCardDTO allCardDTO = new AllCardDTO();
                allCardDTO.setId(card.getId());
                allCardDTO.setCardNo(card.getCardNo());
                allCardDTO.setCardCompany(card.getCardCompany());
                allCardDTO.setCardVaild(card.getCardValid());
                return allCardDTO;
            })
            .collect(Collectors.toList());
    }

    @Override
    public String deleteCard(Long cardId) {
        String cardNo = iCardRepository.getById(cardId).getCardNo();
        iCardRepository.deleteById(cardId);
        return cardNo;
    }

    @Override
    public List<PaymentHistoryDTO> getPayments(Long cardId) {
        List<PaymentHistory> payments = iPaymentHistoryRepository.getByCardId(cardId);
        return payments.stream().map( payment -> {
            PaymentHistoryDTO paymentHistoryDTO = new PaymentHistoryDTO();
            paymentHistoryDTO.setId(payment.getId());
            paymentHistoryDTO.setCardId(payment.getCard().getId());
            paymentHistoryDTO.setPaymentDestination(payment.getPaymentDestination());
            paymentHistoryDTO.setAmount(paymentHistoryDTO.getAmount());
            return paymentHistoryDTO;
        }).collect(Collectors.toList());
    }
}
