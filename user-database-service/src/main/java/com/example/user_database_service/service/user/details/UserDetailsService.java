package com.example.user_database_service.service.user.details;

import lombok.RequiredArgsConstructor;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class UserDetailsService {
    private final DSLContext dslContext;

    public void saveUserDetails(long userId, String name, String surname, int age, String city, boolean status, long registeredAt){
        dslContext.insertInto(Tables.USER_DETAILS)
                .set(Tables.USER_DETAILS.USER_ID, userId)
                .set(Tables.USER_DETAILS.NAME, name)
                .set(Tables.USER_DETAILS.SURNAME, surname)
                .set(Tables.USER_DETAILS.AGE, age)
                .set(Tables.USER_DETAILS.CITY, city)
                .set(Tables.USER_DETAILS.REGISTERED_AT, Instant.ofEpochMilli(registeredAt).atZone(ZoneId.systemDefault()).toLocalDateTime())
                .set(Tables.USER_DETAILS.STATUS, status);
    }
}
