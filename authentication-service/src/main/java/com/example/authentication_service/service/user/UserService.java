package com.example.authentication_service.service.user;

import com.example.authentication_service.model.user.dto.UserRegistrationDTO;
import com.example.authentication_service.service.security.keycloak.KeycloakService;
import com.example.authentication_service.service.user.grpc.UserGrpcClientService;
import com.example.authentication_service.service.user.mapper.grpc.UserDatabaseServiceGrpcMapper;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
        boolean userSaveToDb = userGrpcClientService.registerUser(UserDatabaseServiceGrpcMapper.mapTo(userRegistrationDTO.encodePassword(passwordEncoder().encode(userRegistrationDTO.password())))).getBoolean();
        if (userSaveToDb) {
            keycloakService.createUser(userRegistrationDTO.email(), userRegistrationDTO.password(), userRegistrationDTO.name(), userRegistrationDTO.surname());
        }
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
