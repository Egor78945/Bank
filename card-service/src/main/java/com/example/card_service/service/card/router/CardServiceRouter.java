package com.example.card_service.service.card.router;

import com.example.card_service.enumeration.card.type.CardType;
import com.example.card_service.service.card.CardService;
import com.example.card_service.service.card.CreditCardService;
import com.example.card_service.service.card.DebitCardService;
import com.example.card_service.service.card.grpc.CardGrpcClientService;
import com.example.card_service.service.card.mapper.CardMapper;
import com.example.card_service.service.user.mapper.grpc.UserDatabaseServiceGrpcMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CardServiceRouter {
    private final Map<Long, CardService> routes;
    private final CardGrpcClientService cardGrpcClientService;

    @Autowired
    public CardServiceRouter(CardGrpcClientService cardGrpcClientService, DebitCardService debitCardService, CreditCardService creditCardService) {
        this.cardGrpcClientService = cardGrpcClientService;
        routes = new HashMap<>();
        routes.put(CardType.CARD_DEBIT.getId(), debitCardService);
        routes.put(CardType.CARD_CREDIT.getId(), creditCardService);
    }

    public CardService getCardServiceByCardTypeId(long cardTypeId) {
        return routes.get(cardTypeId);
    }

    public long getCardTypeIdByCardId(long cardId) {
        System.out.println("card service router");
        return cardGrpcClientService.getCardTypeIdByCardId(UserDatabaseServiceGrpcMapper.mapTo(cardId)).getLong();
    }
}
