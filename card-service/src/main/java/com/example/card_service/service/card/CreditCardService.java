package com.example.card_service.service.card;

import com.example.card_service.environment.card.CardEnvironment;
import com.example.card_service.model.kafka.template.KafkaTemplateSender;
import com.example.card_service.model.kafka.template.StringKafkaTemplateSender;
import com.example.card_service.service.card.grpc.CardGrpcClientService;
import com.example.card_service.service.transaction.validation.validator.CardTransactionValidator;
import com.example.card_service.service.transaction.validation.validator.CreditCardTransactionValidator;
import com.example.card_service.service.transaction.validation.validator.router.CardTransactionValidatorRouter;
import com.example.card_service.service.user.mapper.grpc.UserDatabaseServiceGrpcMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreditCardService extends CardService {
    private final CardTransactionValidator cardTransactionValidator;
    private final CardEnvironment cardEnvironment;
    private final CardTransactionValidatorRouter cardTransactionValidatorRouter;
    private final KafkaTemplateSender kafkaTemplateSender;

    public CreditCardService(CardGrpcClientService cardGrpcClientService, CardEnvironment cardEnvironment, CreditCardTransactionValidator cardTransactionValidator, CardTransactionValidatorRouter cardTransactionValidatorRouter, StringKafkaTemplateSender kafkaTemplateSender) {
        super(cardEnvironment, cardGrpcClientService);
        this.cardTransactionValidator = cardTransactionValidator;
        this.cardEnvironment = cardEnvironment;
        this.cardTransactionValidatorRouter = cardTransactionValidatorRouter;
        this.kafkaTemplateSender = kafkaTemplateSender;
    }

    @Override
    protected void doRegister(long cardTypeId, long userId) {
        cardGrpcClientService.registerCardByCardTypeIdAndUserId(UserDatabaseServiceGrpcMapper.mapTo(cardTypeId, userId, cardEnvironment.getCARD_CREDIT_LIMIT(), true));
    }

    @Override
    public void transaction(long cardFromId, long cardToId, double amount) {
        if(cardTransactionValidator.canSend(cardFromId, cardToId, amount)){
            System.out.println("trying to get cardTo card type id");
            long cardToCardTypeId = cardGrpcClientService.getCardTypeIdByCardId(UserDatabaseServiceGrpcMapper.mapTo(cardToId)).getLong();
            System.out.println("got type id, trying validate...");
            if(cardTransactionValidatorRouter.getValidatorMap().get(cardToCardTypeId).canReceive(cardToId, amount)){
                System.out.println("is valid, trying to send...");
                kafkaTemplateSender.send(kafkaTemplateSender.getKafkaEnvironment().getKAFKA_TOPIC_TRANSACTION_NAME(), LocalDateTime.now().toString(), String.format("%s/%s/%s", cardFromId, cardToId, amount));
            }
        } else {
            throw new RuntimeException("card is blocked or not enough money to make transaction");
        }
    }
}
