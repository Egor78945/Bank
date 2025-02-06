package com.example.authentication_service.configuration.security.keycloak;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RoleResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfiguration {
    private final String KEYCLOAK_SERVER_HOST;
    private final String KEYCLOAK_SERVER_PORT;
    private final String KEYCLOAK_REALM_NAME;
    private final String KEYCLOAK_REALM_ROLE_USER;
    private final String KEYCLOAK_REALM_ROLE_ADMIN;
    private final String KEYCLOAK_ADMIN_NAME;
    private final String KEYCLOAK_ADMIN_PASSWORD;
    private final String KEYCLOAK_CLIENT_AUTHENTICATION_SERVICE_ID;
    private final Keycloak keycloak;

    public KeycloakConfiguration(@Value("${keycloak.server.host}") String KEYCLOAK_SERVER_HOST,
                                 @Value("${keycloak.server.port}") String KEYCLOAK_SERVER_PORT,
                                 @Value("${keycloak.realm.bank.name}") String KEYCLOAK_REALM_NAME,
                                 @Value("${keycloak.realm.role.user}") String KEYCLOAK_REALM_ROLE_USER,
                                 @Value("${keycloak.realm.role.admin}") String KEYCLOAK_REALM_ROLE_ADMIN,
                                 @Value("${keycloak.admin.name}") String KEYCLOAK_ADMIN_NAME,
                                 @Value("${keycloak.admin.password}") String KEYCLOAK_ADMIN_PASSWORD,
                                 @Value("${keycloak.client.authentication-service.id}") String KEYCLOAK_CLIENT_AUTHENTICATION_SERVICE_ID) {
        this.KEYCLOAK_SERVER_HOST = KEYCLOAK_SERVER_HOST;
        this.KEYCLOAK_SERVER_PORT = KEYCLOAK_SERVER_PORT;
        this.KEYCLOAK_REALM_NAME = KEYCLOAK_REALM_NAME;
        this.KEYCLOAK_REALM_ROLE_USER = KEYCLOAK_REALM_ROLE_USER;
        this.KEYCLOAK_REALM_ROLE_ADMIN = KEYCLOAK_REALM_ROLE_ADMIN;
        this.KEYCLOAK_ADMIN_NAME = KEYCLOAK_ADMIN_NAME;
        this.KEYCLOAK_ADMIN_PASSWORD = KEYCLOAK_ADMIN_PASSWORD;
        this.KEYCLOAK_CLIENT_AUTHENTICATION_SERVICE_ID = KEYCLOAK_CLIENT_AUTHENTICATION_SERVICE_ID;
        System.out.println(KEYCLOAK_SERVER_HOST);
        System.out.println(KEYCLOAK_SERVER_PORT);
        System.out.println(KEYCLOAK_REALM_NAME);
        System.out.println(KEYCLOAK_REALM_ROLE_USER);
        System.out.println(KEYCLOAK_REALM_ROLE_ADMIN);
        System.out.println(KEYCLOAK_ADMIN_NAME);
        System.out.println(KEYCLOAK_ADMIN_PASSWORD);
        System.out.println(KEYCLOAK_CLIENT_AUTHENTICATION_SERVICE_ID);
        this.keycloak = KeycloakBuilder.builder()
                .serverUrl(String.format("http://%s:%s", KEYCLOAK_SERVER_HOST, KEYCLOAK_SERVER_PORT))
                .realm("master")
                .username(KEYCLOAK_ADMIN_NAME)
                .password(KEYCLOAK_ADMIN_PASSWORD)
                .clientId("admin-cli")
                .grantType(OAuth2Constants.PASSWORD)
                .build();

    }

    public UsersResource getUsersResource(){
        return keycloak.realm(KEYCLOAK_REALM_NAME).users();
    }

    public RoleResource getRoleResource(String role){
        return keycloak.realm(KEYCLOAK_REALM_NAME).roles().get(role);
    }

    public Keycloak getKeycloak() {
        return keycloak;
    }

    public String getKEYCLOAK_SERVER_HOST() {
        return KEYCLOAK_SERVER_HOST;
    }

    public String getKEYCLOAK_SERVER_PORT() {
        return KEYCLOAK_SERVER_PORT;
    }

    public String getKEYCLOAK_REALM_NAME() {
        return KEYCLOAK_REALM_NAME;
    }

    public String getKEYCLOAK_REALM_ROLE_USER() {
        return KEYCLOAK_REALM_ROLE_USER;
    }

    public String getKEYCLOAK_REALM_ROLE_ADMIN() {
        return KEYCLOAK_REALM_ROLE_ADMIN;
    }

    public String getKEYCLOAK_ADMIN_NAME() {
        return KEYCLOAK_ADMIN_NAME;
    }

    public String getKEYCLOAK_ADMIN_PASSWORD() {
        return KEYCLOAK_ADMIN_PASSWORD;
    }

    public String getKEYCLOAK_CLIENT_AUTHENTICATION_SERVICE_ID() {
        return KEYCLOAK_CLIENT_AUTHENTICATION_SERVICE_ID;
    }
}
