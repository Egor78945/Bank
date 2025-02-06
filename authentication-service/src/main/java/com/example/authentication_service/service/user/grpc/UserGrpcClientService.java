package com.example.authentication_service.service.user.grpc;

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

    public UserDatabaseService.LongMessage registerUser(UserDatabaseService.UserMessage userMessage){
        return userServiceBlockingStub.registerUser(userMessage);
    }

    public UserDatabaseService.BooleanMessage removeUser(UserDatabaseService.LongMessage longMessage){
        return userServiceBlockingStub.removeUserById(longMessage);
    }
}
