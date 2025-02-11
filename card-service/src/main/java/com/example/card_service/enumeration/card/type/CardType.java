package com.example.card_service.enumeration.card.type;

public enum CardType {
    CARD_DEBIT(1), CARD_CREDIT(2);

    CardType(long id) {
        this.id = id;
    }

    private final long id;

    public long getId() {
        return id;
    }
}
