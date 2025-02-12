package com.example.card_service.service.card.router;

import com.example.card_service.enumeration.card.type.CardType;
import com.example.card_service.service.card.CardService;
import com.example.card_service.service.card.CreditCardService;
import com.example.card_service.service.card.DebitCardService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CardServiceRouter {
    private final Map<Long, CardService> routes;

    public CardServiceRouter(DebitCardService debitCardService, CreditCardService creditCardService) {
        routes = new HashMap<>();
        routes.put(CardType.CARD_DEBIT.getId(), debitCardService);
        routes.put(CardType.CARD_CREDIT.getId(), creditCardService);
    }

    public CardService getCardServiceByCardTypeId(long cardTypeId){
        return routes.get(cardTypeId);
    }
}
