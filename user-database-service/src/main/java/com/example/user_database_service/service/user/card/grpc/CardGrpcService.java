package com.example.user_database_service.service.user.card.grpc;

import com.example.grpc.user.CardServiceGrpc;
import com.example.grpc.user.UserDatabaseService;
import com.example.user_database_service.service.user.card.UserCardService;
import com.example.user_database_service.service.user.mapper.grpc.UserDatabaseServiceGrpcMapper;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class CardGrpcService extends CardServiceGrpc.CardServiceImplBase {
    private final UserCardService userCardService;

    @Override
    public void existsCardByCardTypeIdAndUserId(UserDatabaseService.DoubleLongMessage request, StreamObserver<UserDatabaseService.BooleanMessage> responseObserver) {
        responseObserver.onNext(UserDatabaseServiceGrpcMapper.mapTo(userCardService.existsCardByCardTypeIdAndUserId(request.getLong1(), request.getLong2())));
        responseObserver.onCompleted();
    }

    @Override
    public void registerCardByCardTypeIdAndUserId(UserDatabaseService.CardRegistrationMessage request, StreamObserver<UserDatabaseService.BooleanMessage> responseObserver) {
        userCardService.registerCardByCardTypeIdAndUserId(request.getCardId(), request.getUserId(), request.getBalance(), request.getStatus());
        responseObserver.onNext(UserDatabaseServiceGrpcMapper.mapTo(true));
        responseObserver.onCompleted();
    }

    @Override
    public void getCardBalanceByCardId(UserDatabaseService.LongMessage request, StreamObserver<UserDatabaseService.DoubleMessage> responseObserver) {
        responseObserver.onNext(UserDatabaseServiceGrpcMapper.mapTo(userCardService.getCardBalanceByCardId(request.getLong())));
        responseObserver.onCompleted();
    }

    @Override
    public void getCardTypeIdByCardId(UserDatabaseService.LongMessage request, StreamObserver<UserDatabaseService.LongMessage> responseObserver) {
        responseObserver.onNext(UserDatabaseServiceGrpcMapper.mapTo(userCardService.getCardTypeIdByCardId(request.getLong())));
    }
}
