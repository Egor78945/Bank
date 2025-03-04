package com.example.card_service.service.card.grpc;

import com.example.grpc.user.CardServiceGrpc;
import com.example.grpc.user.UserDatabaseService;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardGrpcClientService {
    @GrpcClient("user-grpc-service")
    private CardServiceGrpc.CardServiceBlockingStub cardServiceBlockingStub;

    public UserDatabaseService.BooleanMessage existsCardByCardTypeIdAndUserId(UserDatabaseService.DoubleLongMessage doubleLongMessage){
        return cardServiceBlockingStub.existsCardByCardTypeIdAndUserId(doubleLongMessage);
    }

    public UserDatabaseService.BooleanMessage registerCardByCardTypeIdAndUserId(UserDatabaseService.CardRegistrationMessage cardRegistrationMessage){
        return cardServiceBlockingStub.registerCardByCardTypeIdAndUserId(cardRegistrationMessage);
    }

    public UserDatabaseService.DoubleMessage getCardBalanceByCardId(UserDatabaseService.LongMessage longMessage){
        return cardServiceBlockingStub.getCardBalanceByCardId(longMessage);
    }


    public UserDatabaseService.LongMessage getCardTypeIdByCardId(UserDatabaseService.LongMessage longMessage){
        return cardServiceBlockingStub.getCardTypeIdByCardId(longMessage);
    }
}
