package com.coderecipe.v1.card.controller;

import com.coderecipe.global.constant.BaseRes;
import com.coderecipe.v1.card.dto.CardDTO;
import com.coderecipe.v1.card.service.ICardService;
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
@CrossOrigin(origins = "http://localhost:3000")
public class CardController {

    private final ICardService iCardService;

    @PostMapping("/add")
    public ResponseEntity<BaseRes<List<Long>>> addCard(@RequestBody List<CardDTO> cardData){
        List<Long> result = iCardService.addCard(cardData);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }
}
