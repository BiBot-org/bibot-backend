package com.coderecipe.v1.card.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.card.dto.AllCardDTO;
import com.coderecipe.v1.card.dto.CardDTO;
import com.coderecipe.v1.card.dto.vo.CardReq.CardId;
import com.coderecipe.v1.card.service.ICardService;
import com.coderecipe.v1.payment.dto.PaymentHistoryDTO;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/card")
@RequiredArgsConstructor
@Slf4j
public class CardController {

    private final ICardService iCardService;
    UUID userId = UUID.fromString("12f92119-ecee-4bcf-abe8-d01ef389c369");

    @PostMapping
    public ResponseEntity<BaseRes<Long>> addCard(@RequestBody CardDTO cardData) {
        Long result = iCardService.addCard(cardData);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }

//    @GetMapping // TODO: 2023-05-16 상세 조회로 바꾸고 아래의 gerAllCard 변경
//    public ResponseEntity<BaseRes<CardDTO>> getCard(
//        @RequestParam(name = "cardId", defaultValue = "") Long cardId) {
//        CardDTO result = iCardService.getCard(cardId);
//        return ResponseEntity.ok().body(BaseRes.success(result));
//    }

    @GetMapping
    public ResponseEntity<BaseRes<List<PaymentHistoryDTO>>> getPayments(
        @RequestParam(name = "cardId", defaultValue = "") Long cardId) {
        List<PaymentHistoryDTO> result = iCardService.getPayments(cardId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @GetMapping("/all")
    public ResponseEntity<BaseRes<List<AllCardDTO>>> getAllCard() {
        List<AllCardDTO> result = iCardService.getAllCard(userId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @DeleteMapping
    public ResponseEntity<BaseRes<String>> deleteCard(@RequestBody CardId cardId) {
        String result = iCardService.deleteCard(cardId.getId());
        return ResponseEntity.ok().body(BaseRes.success(result));
    }
}