package com.example.card_service.model.kafka.template;

import com.example.card_service.environment.kafka.KafkaEnvironment;
import com.example.card_service.exception.ArgumentRequiredException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StringKafkaTemplateSender extends KafkaTemplateSender {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public StringKafkaTemplateSender(KafkaEnvironment kafkaEnvironment, KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaEnvironment);
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Transactional("stringProducerTransactionManager")
    public void send(String topic, String key, Object value) {
        if(value instanceof String) {
            kafkaTemplate.send(topic, key, (String) value);
        } else {
            throw new ArgumentRequiredException(String.class, value.getClass());
        }
    }
}
