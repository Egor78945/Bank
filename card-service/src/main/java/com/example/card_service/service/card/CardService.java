package com.example.card_service.service.card;

import com.example.card_service.enumeration.card.type.CardType;
import com.example.card_service.environment.card.CardEnvironment;
import com.example.card_service.model.card.dto.CardRegistrationDTO;
import com.example.card_service.service.card.grpc.CardGrpcClientService;
import com.example.card_service.service.card.mapper.CardMapper;
import com.example.card_service.service.user.mapper.grpc.UserDatabaseServiceGrpcMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class CardService {
    protected CardEnvironment cardEnvironment;
    protected CardGrpcClientService cardGrpcClientService;


    public void registerCard(CardRegistrationDTO cardRegistrationDTO) {
        CardType cardType = CardMapper.mapTo(cardRegistrationDTO.cardType());
        if (!cardGrpcClientService.existsCardByCardTypeIdAndUserId(UserDatabaseServiceGrpcMapper.mapTo(cardType.getId(), cardRegistrationDTO.userId())).getBoolean()) {
            doRegister(cardType.getId(), cardRegistrationDTO.userId());
        } else {
            throw new RuntimeException("user already has the card");
        }
    }

    protected abstract void doRegister(long cardTypeId, long userId);
    public abstract void transaction(long cardFromId, long cardToId, double amount);
}
