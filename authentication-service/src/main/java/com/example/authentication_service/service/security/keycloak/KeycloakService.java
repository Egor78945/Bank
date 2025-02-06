package com.example.authentication_service.service.security.keycloak;

import com.example.authentication_service.configuration.security.keycloak.KeycloakConfiguration;
import com.example.authentication_service.enumeration.user.role.UserRole;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KeycloakService {
    private final KeycloakConfiguration keycloakConfiguration;

    private String getClientSecret(String clientId) {
        Keycloak keycloak = keycloakConfiguration.getKeycloak();
        List<ClientRepresentation> clients = keycloak
                .realm(keycloakConfiguration.getKEYCLOAK_REALM_BANK_NAME())
                .clients()
                .findByClientId(clientId);
        if (clients == null || clients.isEmpty()) {
            throw new NullPointerException(String.format("Keycloak client with id %s not found.", clientId));
        }
        ClientRepresentation client = clients.get(0);
        return keycloak.realm(keycloakConfiguration.getKEYCLOAK_REALM_BANK_NAME()).clients().get(client.getId()).getSecret().getValue();
    }

    public void createUser(String email, String password, String name, String surname) throws Exception {
        UsersResource usersResource = keycloakConfiguration.getUsersResource();

        var user = new UserRepresentation();
        user.setEmail(email);
        user.setFirstName(name);
        user.setLastName(surname);
        user.setEnabled(true);
        user.setEmailVerified(true);

        try (Response createUserResponse = usersResource.create(user)) {
            if (createUserResponse.getStatus()/100 != 2) {
                throw new Exception(String.format("There is a problem while creating an user in realm %s with email %s.", keycloakConfiguration.getKEYCLOAK_REALM_BANK_NAME(), email));
            }
            String path = createUserResponse.getLocation().getPath();
            String createdUserId = path.substring(path.lastIndexOf('/') + 1);
            resetPassword(usersResource, createdUserId, password);
            resetRole(usersResource, createdUserId, UserRole.ROLE_USER.name());
        }
    }

    private void resetPassword(UsersResource usersResource, String userId, String password) {
        CredentialRepresentation passwordRepresentation = new CredentialRepresentation();

        passwordRepresentation.setTemporary(false);
        passwordRepresentation.setType(CredentialRepresentation.PASSWORD);
        passwordRepresentation.setValue(password);

        UserResource userResource = usersResource.get(userId);
        userResource.resetPassword(passwordRepresentation);
    }

    private void resetRole(UsersResource usersResource, String userId, String role) {
        RoleRepresentation roleRepresentation = keycloakConfiguration.getRoleResource(role).toRepresentation();
        if (roleRepresentation == null) {
            throw new NullPointerException(String.format("Role '%s' not found in keycloak realm.", role));
        }
        UserResource userResource = usersResource.get(userId);

        userResource.roles().realmLevel().add(Collections.singletonList(roleRepresentation));
    }

}
