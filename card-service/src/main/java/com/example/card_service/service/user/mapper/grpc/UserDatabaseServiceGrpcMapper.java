package com.example.card_service.service.user.mapper.grpc;

import com.example.grpc.user.UserDatabaseService;

public class UserDatabaseServiceGrpcMapper {
    public static UserDatabaseService.DoubleLongMessage mapTo(long long1, long long2){
        return UserDatabaseService.DoubleLongMessage.newBuilder()
                .setLong1(long1)
                .setLong2(long2)
                .build();
    }
    public static UserDatabaseService.CardRegistrationMessage mapTo(long cardId, long userId, double balance, boolean status){
        return UserDatabaseService.CardRegistrationMessage.newBuilder()
                .setCardId(cardId)
                .setUserId(userId)
                .setBalance(balance)
                .setStatus(status)
                .build();
    }
}
