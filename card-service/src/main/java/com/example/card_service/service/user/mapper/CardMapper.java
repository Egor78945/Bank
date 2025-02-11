package com.example.card_service.service.user.mapper;

import com.example.card_service.enumeration.card.type.CardType;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CardMapper {
    private static final Map<String, CardType> cards;

    static {
        Function<CardType, String> getCardName = t -> {
            String[] cardName = t.name().split("_");
            return cardName[cardName.length - 1].toLowerCase();
        };
        cards = Arrays.stream(CardType.values()).collect(Collectors.toMap(getCardName, c -> c));
    }

    public static CardType mapTo(String cardTypeName) {
        CardType cardType = cards.get(cardTypeName);
        System.out.println(cardType == null ? "null" : cardType.name());
        return cardType;
    }
}
