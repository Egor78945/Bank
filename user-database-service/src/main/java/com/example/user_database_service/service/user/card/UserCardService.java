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
        dslContext
                .deleteFrom(Tables.USER_CARDS)
                .where(Tables.USER_CARDS.USER_ID.eq(userId))
                .execute();
    }

    public boolean existsCardByCardTypeIdAndUserId(long cardTypeId, long userId){
        return dslContext
                .fetchExists(dslContext.selectFrom(Tables.USER_CARDS)
                        .where(Tables.USER_CARDS.CARD_TYPE_ID.eq(cardTypeId).and(Tables.USER_CARDS.USER_ID.eq(userId))));
    }

    public void registerCardByCardTypeIdAndUserId(long cardTypeId, long userId, double balance, boolean status){
        dslContext
                .insertInto(Tables.USER_CARDS)
                .set(Tables.USER_CARDS.CARD_TYPE_ID, cardTypeId)
                .set(Tables.USER_CARDS.USER_ID, userId)
                .set(Tables.USER_CARDS.BALANCE, balance)
                .set(Tables.USER_CARDS.STATUS, status)
                .execute();
    }
}
