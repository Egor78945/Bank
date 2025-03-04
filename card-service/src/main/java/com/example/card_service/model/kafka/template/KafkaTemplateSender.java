package com.example.card_service.model.kafka.template;


import com.example.card_service.environment.kafka.KafkaEnvironment;

public abstract class KafkaTemplateSender {
    private final KafkaEnvironment kafkaEnvironment;

    public KafkaTemplateSender(KafkaEnvironment kafkaEnvironment) {
        this.kafkaEnvironment = kafkaEnvironment;
    }

    public abstract void send(String topic, String key, Object value);

    public KafkaEnvironment getKafkaEnvironment() {
        return kafkaEnvironment;
    }
}
