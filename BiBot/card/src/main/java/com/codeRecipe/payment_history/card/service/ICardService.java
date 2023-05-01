package com.codeRecipe.payment_history.card.service;

import com.codeRecipe.payment_history.card.model.Card;
import com.codeRecipe.payment_history.card.model.dto.RequestCard;

public interface ICardService {
    void addCard(RequestCard requestCard);

}
