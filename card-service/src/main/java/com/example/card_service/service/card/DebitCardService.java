package com.example.card_service.service.card;

import com.example.card_service.environment.card.CardEnvironment;
import com.example.card_service.service.user.grpc.UserGrpcClientService;
import com.example.card_service.service.user.mapper.grpc.UserDatabaseServiceGrpcMapper;
import org.springframework.stereotype.Service;

@Service
public class DebitCardService extends CardService {
    public DebitCardService(UserGrpcClientService userGrpcClientService, CardEnvironment cardEnvironment) {
        super(cardEnvironment, userGrpcClientService);
    }

    @Override
    protected void doRegister(long cardTypeId, long userId) {
        userGrpcClientService.registerCardByCardTypeIdAndUserId(UserDatabaseServiceGrpcMapper.mapTo(cardTypeId, userId, 0.0, true));
    }
}
