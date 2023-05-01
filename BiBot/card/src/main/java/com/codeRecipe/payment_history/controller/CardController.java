package com.codeRecipe.payment_history.controller;

import com.codeRecipe.payment_history.card.model.dto.RequestCard;
import com.codeRecipe.payment_history.card.service.ICardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class CardController {
    private final ICardService iCardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody List<RequestCard> requestCards){
        for (RequestCard requestCard : requestCards){
            iCardService.addCard(requestCard);
        }
        return ResponseEntity.status(HttpStatus.OK).body(requestCards);
    }
}
