package com.example.card_service.service.card;

import com.example.card_service.environment.card.CardEnvironment;
import com.example.card_service.model.kafka.template.KafkaTemplateSender;
import com.example.card_service.model.kafka.template.StringKafkaTemplateSender;
import com.example.card_service.service.card.grpc.CardGrpcClientService;
import com.example.card_service.service.transaction.validation.validator.CardTransactionValidator;
import com.example.card_service.service.transaction.validation.validator.DebitCardTransactionValidator;
import com.example.card_service.service.transaction.validation.validator.router.CardTransactionValidatorRouter;
import com.example.card_service.service.user.mapper.grpc.UserDatabaseServiceGrpcMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DebitCardService extends CardService {
    private final KafkaTemplateSender kafkaTemplateSender;
    private final CardTransactionValidator cardTransactionValidator;
    private final CardTransactionValidatorRouter cardTransactionValidatorRouter;

    public DebitCardService(CardGrpcClientService cardGrpcClientService, CardEnvironment cardEnvironment, StringKafkaTemplateSender kafkaTemplateSender, DebitCardTransactionValidator cardTransactionValidator, CardTransactionValidatorRouter cardTransactionValidatorRouter) {
        super(cardEnvironment, cardGrpcClientService);
        this.kafkaTemplateSender = kafkaTemplateSender;
        this.cardTransactionValidator = cardTransactionValidator;
        this.cardTransactionValidatorRouter = cardTransactionValidatorRouter;
    }

    @Override
    protected void doRegister(long cardTypeId, long userId) {
        cardGrpcClientService.registerCardByCardTypeIdAndUserId(UserDatabaseServiceGrpcMapper.mapTo(cardTypeId, userId, 0.0, true));
    }

    @Override
    public void transaction(long cardFromId, long cardToId, double amount) {
        if (cardTransactionValidator.canSend(cardFromId, cardToId, amount)) {
            long cardToCardTypeId = cardGrpcClientService.getCardTypeIdByCardId(UserDatabaseServiceGrpcMapper.mapTo(cardToId)).getLong();
            if (cardTransactionValidatorRouter.getValidatorMap().get(cardToCardTypeId).canReceive(cardToId, amount)) {
                kafkaTemplateSender.send(kafkaTemplateSender.getKafkaEnvironment().getKAFKA_TOPIC_TRANSACTION_NAME(), LocalDateTime.now().toString(), String.format("%s/%s/%s", cardFromId, cardToId, amount));
            }
        } else {
            throw new RuntimeException("card is blocked or not enough money to make transaction");
        }
    }
}
