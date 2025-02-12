package com.example.card_service.service.card;

import com.example.card_service.enumeration.card.type.CardType;
import com.example.card_service.environment.card.CardEnvironment;
import com.example.card_service.model.card.dto.CardRegistrationDTO;
import com.example.card_service.service.user.grpc.UserGrpcClientService;
import com.example.card_service.service.user.mapper.CardMapper;
import com.example.card_service.service.user.mapper.grpc.UserDatabaseServiceGrpcMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class CardService {
    protected CardEnvironment cardEnvironment;
    protected UserGrpcClientService userGrpcClientService;


    public void registerCard(CardRegistrationDTO cardRegistrationDTO) {
        CardType cardType = CardMapper.mapTo(cardRegistrationDTO.cardType());
        if (!userGrpcClientService.existsCardByCardTypeIdAndUserId(UserDatabaseServiceGrpcMapper.mapTo(cardType.getId(), cardRegistrationDTO.userId())).getBoolean()) {
            doRegister(cardType.getId(), cardRegistrationDTO.userId());
        } else {
            throw new RuntimeException("user already has the card");
        }
    }

    protected abstract void doRegister(long cardTypeId, long userId);
}
