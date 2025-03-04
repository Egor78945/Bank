package com.example.card_service.service.transaction.validation.validator;

import com.example.card_service.service.card.grpc.CardGrpcClientService;
import com.example.card_service.service.user.mapper.grpc.UserDatabaseServiceGrpcMapper;

public abstract class CardTransactionValidator {
    protected final CardGrpcClientService cardGrpcClientService;

    public CardTransactionValidator(CardGrpcClientService cardGrpcClientService) {
        this.cardGrpcClientService = cardGrpcClientService;
    }
    public boolean canSend(long fromId, double amount) {
        return amount > 0 && cardGrpcClientService.getCardBalanceByCardId(UserDatabaseServiceGrpcMapper.mapTo(fromId)).getDoubl() - amount >= 0;
    }
    public abstract boolean canReceive(long toId, double amount);
}
