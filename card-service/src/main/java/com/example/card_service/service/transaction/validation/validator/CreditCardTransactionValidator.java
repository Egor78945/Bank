package com.example.card_service.service.transaction.validation.validator;

import com.example.card_service.environment.card.CardEnvironment;
import com.example.card_service.service.card.grpc.CardGrpcClientService;
import com.example.card_service.service.user.mapper.grpc.UserDatabaseServiceGrpcMapper;
import org.springframework.stereotype.Component;

@Component
public class CreditCardTransactionValidator extends CardTransactionValidator {
    private final CardEnvironment cardEnvironment;

    public CreditCardTransactionValidator(CardGrpcClientService cardGrpcClientService, CardEnvironment cardEnvironment) {
        super(cardGrpcClientService);
        this.cardEnvironment = cardEnvironment;
    }

    @Override
    public boolean canReceive(long toId, double amount) {
        return cardGrpcClientService.getCardBalanceByCardId(UserDatabaseServiceGrpcMapper.mapTo(toId)).getDoubl() + amount <= cardEnvironment.getCARD_CREDIT_LIMIT();
    }
}
