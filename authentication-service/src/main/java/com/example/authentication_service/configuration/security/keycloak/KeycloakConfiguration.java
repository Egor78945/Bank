package com.example.authentication_service.configuration.security.keycloak;

import com.example.authentication_service.environment.keycloak.KeycloakEnvironment;
import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RoleResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfiguration {
    private final KeycloakEnvironment keycloakEnvironment;
    private final Keycloak keycloak;

    public KeycloakConfiguration(KeycloakEnvironment keycloakEnvironment) {
        this.keycloakEnvironment = keycloakEnvironment;
        this.keycloak = KeycloakBuilder.builder()
                .serverUrl(String.format("http://%s:%s", keycloakEnvironment.getKEYCLOAK_SERVER_HOST(), keycloakEnvironment.getKEYCLOAK_SERVER_PORT()))
                .realm(keycloakEnvironment.getKEYCLOAK_REALM_MASTER_NAME())
                .username(keycloakEnvironment.getKEYCLOAK_ADMIN_NAME())
                .password(keycloakEnvironment.getKEYCLOAK_ADMIN_PASSWORD())
                .clientId(keycloakEnvironment.getKEYCLOAK_CLIENT_ADMIN_ID())
                .grantType(OAuth2Constants.PASSWORD)
                .build();
    }

    public UsersResource getUsersResource(){
        return keycloak.realm(keycloakEnvironment.getKEYCLOAK_REALM_BANK_NAME()).users();
    }

    public RoleResource getRoleResource(String role){
        return keycloak.realm(keycloakEnvironment.getKEYCLOAK_REALM_BANK_NAME()).roles().get(role);
    }

    public Keycloak getKeycloak() {
        return keycloak;
    }
}
