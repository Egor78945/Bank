package com.example.card_service.service.transaction.validation.validator.router;

import com.example.card_service.enumeration.card.type.CardType;
import com.example.card_service.service.transaction.validation.validator.CardTransactionValidator;
import com.example.card_service.service.transaction.validation.validator.CreditCardTransactionValidator;
import com.example.card_service.service.transaction.validation.validator.DebitCardTransactionValidator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CardTransactionValidatorRouter {
    private final Map<Long, CardTransactionValidator> validatorMap;

    public CardTransactionValidatorRouter(CreditCardTransactionValidator creditCardTransactionValidator, DebitCardTransactionValidator debitCardTransactionValidator) {
        this.validatorMap = new HashMap<>();
        validatorMap.put(CardType.CARD_CREDIT.getId(), creditCardTransactionValidator);
        validatorMap.put(CardType.CARD_DEBIT.getId(), debitCardTransactionValidator);
    }

    public Map<Long, CardTransactionValidator> getValidatorMap() {
        return validatorMap;
    }
}
