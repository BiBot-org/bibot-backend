package com.coderecipe.v1.card.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.card.dto.vo.CardReq.*;
import com.coderecipe.v1.card.dto.vo.CardRes.*;
import com.coderecipe.v1.card.service.ICardService;

import java.security.Principal;
import java.time.LocalDateTime;
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

    @PostMapping
    public ResponseEntity<BaseRes<Long>> addCard(@RequestBody CreateCard req, Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        Long result = iCardService.addCard(req, userId);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }

    @GetMapping("/all")
    public ResponseEntity<BaseRes<List<CardInfoRes>>> getAllCard(Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        List<CardInfoRes> result = iCardService.getAllCard(userId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @DeleteMapping
    public ResponseEntity<BaseRes<Long>> deleteCard(@RequestParam(value = "id", defaultValue = "") Long cardId, Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        Long result = iCardService.deleteCard(cardId, userId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @GetMapping("/amount")
    public ResponseEntity<BaseRes<Integer>> getAmount(
        @RequestParam(value = "cardId", defaultValue="")Long cardId, @RequestParam(value = "startDateTime", defaultValue="") LocalDateTime startDateTime,  @RequestParam(value = "endDateTime", defaultValue="") LocalDateTime endDateTime ) {
        Integer result = iCardService.getAmount(cardId, startDateTime, endDateTime);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }
}