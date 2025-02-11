package com.example.user_database_service.service.user.grpc;

import com.example.grpc.user.UserDatabaseService;
import com.example.grpc.user.UserServiceGrpc;
import com.example.user_database_service.service.user.UserService;
import com.example.user_database_service.service.user.card.UserCardService;
import com.example.user_database_service.service.user.detail.UserDetailsService;
import com.example.user_database_service.service.user.mapper.grpc.UserDatabaseServiceGrpcMapper;
import com.example.user_database_service.service.user.role.UserRoleService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class UserGrpcServerService extends UserServiceGrpc.UserServiceImplBase {
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final UserRoleService userRoleService;
    private final UserCardService userCardService;

    @Override
    public void existsUserByEmail(UserDatabaseService.StringMessage request, StreamObserver<UserDatabaseService.BooleanMessage> responseObserver) {
        responseObserver.onNext(UserDatabaseServiceGrpcMapper.mapTo(userService.existsUserByEmail(request.getString())));
        responseObserver.onCompleted();
    }

    @Override
    public void registerUser(UserDatabaseService.UserMessage request, StreamObserver<UserDatabaseService.LongMessage> responseObserver) {
        long userId = userService.saveUser(request.getEmail(), request.getPassword());
        userDetailsService.saveUserDetails(userId, request.getName(), request.getSurname(), request.getAge(), request.getCity(), request.getStatus(), request.getRegisteredAt());
        for(long roleId: request.getRolesList()){
            userRoleService.saveUserRole(userId, roleId);
        }
        responseObserver.onNext(UserDatabaseServiceGrpcMapper.mapTo(userId));
        responseObserver.onCompleted();
    }

    @Override
    public void removeUserById(UserDatabaseService.LongMessage request, StreamObserver<UserDatabaseService.BooleanMessage> responseObserver) {
        userRoleService.removeUserRoleByUserId(request.getLong());
        userDetailsService.removeUserDetailsByUserId(request.getLong());
        userCardService.removeUserCardsByUserId(request.getLong());
        userService.removeUserById(request.getLong());
        responseObserver.onNext(UserDatabaseServiceGrpcMapper.mapTo(true));
        responseObserver.onCompleted();
    }

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
}
