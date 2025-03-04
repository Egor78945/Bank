package com.example.card_service.service.card.mapper;

import com.example.card_service.enumeration.card.type.CardType;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CardMapper {
    private static final Map<String, CardType> cardTypeByName;
    private static final Map<Long, CardType> cardTypeById;

    static {
        Function<CardType, String> getCardName = t -> {
            String[] cardName = t.name().split("_");
            return cardName[cardName.length - 1].toLowerCase();
        };
        cardTypeByName = Arrays.stream(CardType.values()).collect(Collectors.toMap(getCardName, c -> c));
        cardTypeById = Arrays.stream(CardType.values()).collect(Collectors.toMap(CardType::getId, c->c));
    }

    public static CardType mapTo(String cardTypeName) {
        return cardTypeByName.get(cardTypeName);
    }

    public static CardType mapTo(long id){
        return cardTypeById.get(id);
    }
}
