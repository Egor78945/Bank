package com.example.user_database_service.service.user.mapper.grpc;

import com.example.grpc.user.UserDatabaseService;

public class UserDatabaseServiceGrpcMapper {
    public static UserDatabaseService.StringMessage mapTo(String string){
        return UserDatabaseService.StringMessage.newBuilder()
                .setString(string)
                .build();
    }

    public static String mapTo(UserDatabaseService.StringMessage string){
        return string.getString();
    }

    public static UserDatabaseService.BooleanMessage mapTo(boolean bool){
        return UserDatabaseService.BooleanMessage.newBuilder()
                .setBoolean(bool)
                .build();
    }

    public static boolean mapTo(UserDatabaseService.BooleanMessage bool){
        return bool.getBoolean();
    }

    public static UserDatabaseService.LongMessage mapTo(long lonk){
        return UserDatabaseService.LongMessage.newBuilder()
                .setLong(lonk)
                .build();
    }

    public static UserDatabaseService.DoubleMessage mapTo(double doubl){
        return UserDatabaseService.DoubleMessage.newBuilder()
                .setDoubl(doubl)
                .build();
    }
}
