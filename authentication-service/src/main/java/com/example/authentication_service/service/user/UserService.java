package com.example.authentication_service.service.user;

import com.example.authentication_service.model.user.dto.UserRegistrationDTO;
import com.example.authentication_service.service.user.grpc.UserGrpcClientService;
import com.example.authentication_service.service.user.mapper.grpc.UserDatabaseServiceGrpcMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserGrpcClientService userGrpcClientService;

    public boolean existsUserByEmail(String email) {
        return UserDatabaseServiceGrpcMapper.mapTo(userGrpcClientService.existsUserByEmail(UserDatabaseServiceGrpcMapper.mapTo(email)));
    }

    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        userGrpcClientService.registerUser(UserDatabaseServiceGrpcMapper.mapTo(userRegistrationDTO.encodePassword(passwordEncoder().encode(userRegistrationDTO.password()))));
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
