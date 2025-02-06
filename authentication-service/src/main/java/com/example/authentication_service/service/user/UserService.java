package com.example.authentication_service.service.user;

import com.example.authentication_service.model.user.dto.UserRegistrationDTO;
import com.example.authentication_service.service.security.keycloak.KeycloakService;
import com.example.authentication_service.service.user.grpc.UserGrpcClientService;
import com.example.authentication_service.service.user.mapper.grpc.UserDatabaseServiceGrpcMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserGrpcClientService userGrpcClientService;
    private final KeycloakService keycloakService;

    public boolean existsUserByEmail(String email) {
        return UserDatabaseServiceGrpcMapper.mapTo(userGrpcClientService.existsUserByEmail(UserDatabaseServiceGrpcMapper.mapTo(email)));
    }

    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        long savedUserId = userGrpcClientService.registerUser(UserDatabaseServiceGrpcMapper.mapTo(userRegistrationDTO)).getLong();
        try {
            keycloakService.createUser(userRegistrationDTO.email(), userRegistrationDTO.password(), userRegistrationDTO.name(), userRegistrationDTO.surname());
        } catch (Exception e) {
            userGrpcClientService.removeUser(UserDatabaseServiceGrpcMapper.mapTo(savedUserId));
        }
    }
}
