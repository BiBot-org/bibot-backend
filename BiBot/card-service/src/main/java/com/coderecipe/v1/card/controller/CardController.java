package com.coderecipe.v1.card.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.card.dto.vo.CardReq.*;
import com.coderecipe.v1.card.dto.vo.CardRes.*;
import com.coderecipe.v1.card.service.ICardService;

import java.util.UUID;

import com.coderecipe.v1.payment.dto.vo.PaymentRes.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties.Request;
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
    public ResponseEntity<BaseRes<Long>> addCard(@RequestBody CreateCard req) {
        Long result = iCardService.addCard(req);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }

    @GetMapping
    public ResponseEntity<BaseRes<List<PaymentInfo>>> getPayments(
            @RequestBody RequestGetPayments req) {
        List<PaymentInfo> result = iCardService.getPayments(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @GetMapping("/all")
    public ResponseEntity<BaseRes<List<CardInfoRes>>> getAllCard() {
        List<CardInfoRes> result = iCardService.getAllCard(userId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @DeleteMapping
    public ResponseEntity<BaseRes<Long>> deleteCard(@RequestBody CardId cardId) {
        Long result = iCardService.deleteCard(cardId.getId());
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @GetMapping("/amount")
    public ResponseEntity<BaseRes<Integer>> getAmount(
        @RequestBody RequestGetPayments req) {
        Integer result = iCardService.getAmount(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }
}