package com.example.user_database_service.service.user.card;

import lombok.RequiredArgsConstructor;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCardService {
    private final DSLContext dslContext;

    public void removeUserCardsByUserId(long userId) {
        dslContext
                .deleteFrom(Tables.USER_CARDS)
                .where(Tables.USER_CARDS.USER_ID.eq(userId))
                .execute();
    }

    public boolean existsCardByCardTypeIdAndUserId(long cardTypeId, long userId) {
        return dslContext
                .fetchExists(dslContext.selectFrom(Tables.USER_CARDS)
                        .where(Tables.USER_CARDS.CARD_TYPE_ID.eq(cardTypeId).and(Tables.USER_CARDS.USER_ID.eq(userId))));
    }

    public void registerCardByCardTypeIdAndUserId(long cardTypeId, long userId, double balance, boolean status) {
        dslContext
                .insertInto(Tables.USER_CARDS)
                .set(Tables.USER_CARDS.CARD_TYPE_ID, cardTypeId)
                .set(Tables.USER_CARDS.USER_ID, userId)
                .set(Tables.USER_CARDS.BALANCE, balance)
                .set(Tables.USER_CARDS.STATUS, status)
                .execute();
    }

    public double getCardBalanceByCardId(long cardId) {
        return
                dslContext
                        .select(Tables.USER_CARDS.BALANCE)
                        .from(Tables.USER_CARDS)
                        .where(Tables.USER_CARDS.ID.eq(cardId))
                        .fetchOptional()
                        .orElseThrow(() -> new RuntimeException(String.format("card with id %s is not found", cardId)))
                        .get(Tables.USER_CARDS.BALANCE);
    }

    public long getCardTypeIdByCardId(long cardId){
        return
                dslContext
                        .select(Tables.CARD_TYPES.ID)
                        .from(Tables.CARD_TYPES)
                        .join(Tables.USER_CARDS)
                        .on(Tables.CARD_TYPES.ID.eq(Tables.USER_CARDS.CARD_TYPE_ID))
                        .fetchOptional()
                        .orElseThrow(() -> new RuntimeException("there is a problem while get card type by card id"))
                        .get(Tables.CARD_TYPES.ID);
    }
}
