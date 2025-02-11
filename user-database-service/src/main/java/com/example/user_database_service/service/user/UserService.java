package com.example.user_database_service.service.user;

import lombok.RequiredArgsConstructor;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final DSLContext dslContext;

    public boolean existsUserByEmail(String email) {
        return dslContext
                .fetchExists(dslContext.selectFrom(Tables.USERS)
                        .where(Tables.USERS.EMAIL.eq(email)));
    }

    public long saveUser(String email, String password){
        return dslContext
                .insertInto(Tables.USERS)
                .set(Tables.USERS.EMAIL, email)
                .set(Tables.USERS.PASSWORD, password)
                .returning(Tables.USERS.ID)
                .fetchOne()
                .get(Tables.USERS.ID);
    }

    public void removeUserById(long id){
        dslContext
                .deleteFrom(Tables.USERS)
                .where(Tables.USERS.ID.eq(id))
                .execute();
    }
}
