package com.example.authentication_service.service.user;

import com.example.authentication_service.service.user.grpc.UserGrpcClientService;
import com.example.authentication_service.service.user.mapper.grpc.UserDatabaseServiceGrpcMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserGrpcClientService userGrpcClientService;

    public boolean existsUserByEmail(String email){
        return UserDatabaseServiceGrpcMapper.mapTo(userGrpcClientService.existsUserByEmail(UserDatabaseServiceGrpcMapper.mapTo(email)));
    }
}
