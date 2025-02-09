package com.example.authentication_service.service.user;

import com.example.authentication_service.environment.keycloak.KeycloakEnvironment;
import com.example.authentication_service.model.user.dto.UserRegistrationDTO;
import com.example.authentication_service.service.security.keycloak.KeycloakService;
import com.example.authentication_service.service.user.grpc.UserGrpcClientService;
import com.example.authentication_service.service.user.mapper.grpc.UserDatabaseServiceGrpcMapper;
import com.example.authentication_service.service.web.WebClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserGrpcClientService userGrpcClientService;
    private final KeycloakEnvironment keycloakEnvironment;
    private final KeycloakService keycloakService;
    private final WebClientService webClientService;

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

    public String authenticateUser(String email, String password){
        String clientSecret = keycloakService.getClientSecret(keycloakEnvironment.getKEYCLOAK_CLIENT_AUTHENTICATION_SERVICE_ID());
        String url = String.format("http://%s:%s/realms/%s/protocol/openid-connect/token", keycloakEnvironment.getKEYCLOAK_SERVER_HOST(), keycloakEnvironment.getKEYCLOAK_SERVER_PORT(), keycloakEnvironment.getKEYCLOAK_REALM_BANK_NAME());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", keycloakEnvironment.getKEYCLOAK_CLIENT_AUTHENTICATION_SERVICE_ID());
        body.add("client_secret", clientSecret);
        body.add("grant_type", "password");
        body.add("username", email);
        body.add("password", password);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, httpHeaders);

        ResponseEntity<Map> responseEntity = webClientService.postForEntity(request, url, Map.class);

        if(responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null){
            Object token = responseEntity.getBody().get("access_token");
            if(token != null){
                return token.toString();
            }
        }
        throw new RuntimeException(responseEntity.getBody().toString());
    }
}
