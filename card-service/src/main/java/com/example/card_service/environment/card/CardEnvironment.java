package com.example.card_service.environment.card;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CardEnvironment {
    private final int CARD_CREDIT_LIMIT;

    public CardEnvironment(@Value("${card.credit.limit}") int CARD_CREDIT_LIMIT) {
        this.CARD_CREDIT_LIMIT = CARD_CREDIT_LIMIT;
    }

    public int getCARD_CREDIT_LIMIT() {
        return CARD_CREDIT_LIMIT;
    }
}
