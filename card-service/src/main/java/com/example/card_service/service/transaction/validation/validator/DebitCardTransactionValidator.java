package com.example.card_service.service.transaction.validation.validator;

import com.example.card_service.environment.card.CardEnvironment;
import com.example.card_service.service.card.grpc.CardGrpcClientService;
import org.springframework.stereotype.Component;

@Component
public class DebitCardTransactionValidator extends CardTransactionValidator{
    private final CardEnvironment cardEnvironment;

    public DebitCardTransactionValidator(CardGrpcClientService cardGrpcClientService, CardEnvironment cardEnvironment) {
        super(cardGrpcClientService);
        this.cardEnvironment = cardEnvironment;
    }

    @Override
    public boolean canReceive(long toId, double amount) {
        return true;
    }
}
