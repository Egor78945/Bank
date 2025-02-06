package com.example.authentication_service.service.user.mapper.grpc;

import com.example.authentication_service.enumeration.user.role.UserRole;
import com.example.authentication_service.model.user.dto.UserRegistrationDTO;
import com.example.grpc.user.UserDatabaseService;

import java.util.List;

public class UserDatabaseServiceGrpcMapper {
    public static UserDatabaseService.StringMessage mapTo(String string) {
        return UserDatabaseService.StringMessage.newBuilder()
                .setString(string)
                .build();
    }

    public static String mapTo(UserDatabaseService.StringMessage string) {
        return string.getString();
    }

    public static UserDatabaseService.BooleanMessage mapTo(boolean bool) {
        return UserDatabaseService.BooleanMessage.newBuilder()
                .setBoolean(bool)
                .build();
    }

    public static boolean mapTo(UserDatabaseService.BooleanMessage bool) {
        return bool.getBoolean();
    }

    public static UserDatabaseService.UserMessage mapTo(UserRegistrationDTO userRegistrationDTO) {
        return UserDatabaseService.UserMessage.newBuilder()
                .setEmail(userRegistrationDTO.email())
                .setPassword(userRegistrationDTO.password())
                .setName(userRegistrationDTO.name())
                .setSurname(userRegistrationDTO.surname())
                .setAge(userRegistrationDTO.age())
                .setCity(userRegistrationDTO.city())
                .setStatus(true)
                .setRegisteredAt(System.currentTimeMillis())
                .addAllRoles(List.of(UserRole.ROLE_USER.getId()))
                .build();
    }

    public static UserDatabaseService.LongMessage mapTo(long lonk){
        return UserDatabaseService.LongMessage.newBuilder()
                .setLong(lonk)
                .build();
    }
}
