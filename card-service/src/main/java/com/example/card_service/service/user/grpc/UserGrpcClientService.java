package com.example.card_service.service.user.grpc;

import com.example.grpc.user.UserDatabaseService;
import com.example.grpc.user.UserServiceGrpc;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGrpcClientService {
    @GrpcClient("user-grpc-service")
    private UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    public UserDatabaseService.BooleanMessage existsUserByEmail(UserDatabaseService.StringMessage stringMessage){
        return userServiceBlockingStub.existsUserByEmail(stringMessage);
    }

    public UserDatabaseService.BooleanMessage existsCardByCardTypeIdAndUserId(UserDatabaseService.DoubleLongMessage doubleLongMessage){
        return userServiceBlockingStub.existsCardByCardTypeIdAndUserId(doubleLongMessage);
    }

    public UserDatabaseService.BooleanMessage registerCardByCardTypeIdAndUserId(UserDatabaseService.CardRegistrationMessage cardRegistrationMessage){
        return userServiceBlockingStub.registerCardByCardTypeIdAndUserId(cardRegistrationMessage);
    }
}
