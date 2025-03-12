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
        System.out.println("before if block");
        System.out.println("before sending");
        kafkaTemplate.send(topic, key, (String) value);
        System.out.println("after sending");
    }
}
