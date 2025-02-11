package com.example.card_service.service.card;

import com.example.card_service.enumeration.card.type.CardType;
import com.example.card_service.model.card.dto.CardRegistrationDTO;
import com.example.card_service.service.user.grpc.UserGrpcClientService;
import com.example.card_service.service.user.mapper.CardMapper;
import com.example.card_service.service.user.mapper.grpc.UserDatabaseServiceGrpcMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {
    private final UserGrpcClientService userGrpcClientService;

    public void registerCard(CardRegistrationDTO cardRegistrationDTO) {
        CardType cardType = CardMapper.mapTo(cardRegistrationDTO.cardType());
        if(!userGrpcClientService.existsCardByCardTypeIdAndUserId(UserDatabaseServiceGrpcMapper.mapTo(cardType.getId(), cardRegistrationDTO.userId())).getBoolean()) {
            userGrpcClientService.registerCardByCardTypeIdAndUserId(UserDatabaseServiceGrpcMapper.mapTo(cardType.getId(), cardRegistrationDTO.userId(), 0.0, true));
        } else {
            throw new RuntimeException("user already has the card");
        }
    }
}
