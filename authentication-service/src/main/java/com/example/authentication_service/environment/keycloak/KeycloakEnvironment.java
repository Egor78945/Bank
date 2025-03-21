package com.example.authentication_service.environment.keycloak;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakEnvironment {
    private final String KEYCLOAK_SERVER_HOST;
    private final String KEYCLOAK_SERVER_PORT;
    private final String KEYCLOAK_REALM_BANK_NAME;
    private final String KEYCLOAK_REALM_MASTER_NAME;
    private final String KEYCLOAK_REALM_ROLE_USER;
    private final String KEYCLOAK_REALM_ROLE_ADMIN;
    private final String KEYCLOAK_ADMIN_NAME;
    private final String KEYCLOAK_ADMIN_PASSWORD;
    private final String KEYCLOAK_CLIENT_AUTHENTICATION_SERVICE_ID;
    private final String KEYCLOAK_CLIENT_ADMIN_ID;

    public KeycloakEnvironment(@Value("${keycloak.server.host}") String KEYCLOAK_SERVER_HOST,
                                 @Value("${keycloak.server.port}") String KEYCLOAK_SERVER_PORT,
                                 @Value("${keycloak.realm.bank.name}") String KEYCLOAK_REALM_BANK_NAME,
                                 @Value("${keycloak.realm.bank.role.user}") String KEYCLOAK_REALM_ROLE_USER,
                                 @Value("${keycloak.realm.bank.role.admin}") String KEYCLOAK_REALM_ROLE_ADMIN,
                                 @Value("${keycloak.admin.name}") String KEYCLOAK_ADMIN_NAME,
                                 @Value("${keycloak.admin.password}") String KEYCLOAK_ADMIN_PASSWORD,
                                 @Value("${keycloak.client.authentication-service.id}") String KEYCLOAK_CLIENT_AUTHENTICATION_SERVICE_ID,
                                 @Value("${keycloak.realm.master.name}") String KEYCLOAK_REALM_MASTER_NAME,
                                 @Value("${keycloak.client.admin.id}") String KEYCLOAK_CLIENT_ADMIN_ID) {
        this.KEYCLOAK_SERVER_HOST = KEYCLOAK_SERVER_HOST;
        this.KEYCLOAK_SERVER_PORT = KEYCLOAK_SERVER_PORT;
        this.KEYCLOAK_REALM_BANK_NAME = KEYCLOAK_REALM_BANK_NAME;
        this.KEYCLOAK_REALM_ROLE_USER = KEYCLOAK_REALM_ROLE_USER;
        this.KEYCLOAK_REALM_ROLE_ADMIN = KEYCLOAK_REALM_ROLE_ADMIN;
        this.KEYCLOAK_ADMIN_NAME = KEYCLOAK_ADMIN_NAME;
        this.KEYCLOAK_ADMIN_PASSWORD = KEYCLOAK_ADMIN_PASSWORD;
        this.KEYCLOAK_CLIENT_AUTHENTICATION_SERVICE_ID = KEYCLOAK_CLIENT_AUTHENTICATION_SERVICE_ID;
        this.KEYCLOAK_REALM_MASTER_NAME = KEYCLOAK_REALM_MASTER_NAME;
        this.KEYCLOAK_CLIENT_ADMIN_ID = KEYCLOAK_CLIENT_ADMIN_ID;
    }

    public String getKEYCLOAK_SERVER_HOST() {
        return KEYCLOAK_SERVER_HOST;
    }

    public String getKEYCLOAK_SERVER_PORT() {
        return KEYCLOAK_SERVER_PORT;
    }

    public String getKEYCLOAK_REALM_BANK_NAME() {
        return KEYCLOAK_REALM_BANK_NAME;
    }

    public String getKEYCLOAK_REALM_MASTER_NAME() {
        return KEYCLOAK_REALM_MASTER_NAME;
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

    public String getKEYCLOAK_CLIENT_ADMIN_ID() {
        return KEYCLOAK_CLIENT_ADMIN_ID;
    }
}
