package com.example.user_database_service.service.user.card;

import lombok.RequiredArgsConstructor;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCardService {
    private final DSLContext dslContext;

    public void removeUserCardsByUserId(long userId){
        dslContext.deleteFrom(Tables.USER_CARDS).where(Tables.USER_CARDS.USER_ID.eq(userId));
    }
}
