package com.coderecipe.v1.card.service;

import com.coderecipe.v1.card.dto.CardDTO;

import java.util.List;

public interface ICardService {
    List<Long> addCard(List<CardDTO> req);

}
