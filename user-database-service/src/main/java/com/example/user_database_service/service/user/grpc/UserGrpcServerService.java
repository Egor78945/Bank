package com.example.user_database_service.service.user.grpc;

import com.example.grpc.user.UserDatabaseService;
import com.example.grpc.user.UserServiceGrpc;
import com.example.user_database_service.service.user.mapper.grpc.UserDatabaseServiceGrpcMapper;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class UserGrpcServerService extends UserServiceGrpc.UserServiceImplBase {
    @Override
    public void existsUserByEmail(UserDatabaseService.StringMessage request, StreamObserver<UserDatabaseService.BooleanMessage> responseObserver) {
        responseObserver.onNext(UserDatabaseServiceGrpcMapper.mapTo(true));
        responseObserver.onCompleted();
    }
}
