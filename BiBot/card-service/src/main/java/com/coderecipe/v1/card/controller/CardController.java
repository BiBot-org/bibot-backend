package com.coderecipe.v1.card.controller;

import com.coderecipe.global.constant.dto.BaseRes;
import com.coderecipe.v1.card.dto.CardDTO;
import com.coderecipe.v1.card.dto.vo.CardReq.CardId;
import com.coderecipe.v1.card.dto.vo.CardReq.CreateCard;
import com.coderecipe.v1.card.dto.vo.CardRes.CardInfoRes;
import com.coderecipe.v1.card.service.ICardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Tag(name = "카드 Service API", description = "카드 Service API 문서 입니다.")
@RestController
@RequestMapping("/api/v1/card")
@RequiredArgsConstructor
@Slf4j
public class CardController {

    private final ICardService iCardService;

    @Operation(summary = "카드 추가", description = "새로운 카드 추가 API 입니다.")
    @PostMapping
    public ResponseEntity<BaseRes<Long>> addCard(@RequestBody CreateCard req, Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        Long result = iCardService.addCard(req, userId);
        return ResponseEntity.status(HttpStatus.OK).body(BaseRes.success(result));
    }

    @Operation(summary = "카드 전체 조회", description = "유저가 보유한 카드 전체 조회 API 입니다.")
    @GetMapping("/all")
    public ResponseEntity<BaseRes<List<CardInfoRes>>> getAllCard(Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        List<CardInfoRes> result = iCardService.getAllCard(userId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "카드 정보 단건 조회", description = "카드 정보 단건 조회 API 입니다.")
    @GetMapping
    public ResponseEntity<BaseRes<CardDTO>> getCardInfo(@RequestParam(value = "id", defaultValue = "") Long cardId) {
        CardDTO result = iCardService.getCard(cardId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "카드 삭제", description = "카드 삭제 API 입니다.")
    @DeleteMapping
    public ResponseEntity<BaseRes<Long>> deleteCard(@RequestBody CardId cardId, Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        Long result = iCardService.deleteCard(cardId.getId(), userId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "기간 별 카드 사용 총 합 조회", description = "기간 별 카드 사용 총 합계 조회 API 입니다.")
    @GetMapping("/amount")
    public ResponseEntity<BaseRes<Integer>> getAmount(
            @RequestParam(value = "cardId", defaultValue = "") Long cardId, @RequestParam(value = "startDateTime", defaultValue = "") LocalDateTime startDateTime, @RequestParam(value = "endDateTime", defaultValue = "") LocalDateTime endDateTime) {
        Integer result = iCardService.getAmount(cardId, startDateTime, endDateTime);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }
}