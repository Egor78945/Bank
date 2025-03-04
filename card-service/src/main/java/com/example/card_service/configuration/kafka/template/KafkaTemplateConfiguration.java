package com.example.card_service.configuration.kafka.template;

import com.example.card_service.environment.kafka.KafkaEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@RequiredArgsConstructor
public class KafkaTemplateConfiguration {
    private final KafkaEnvironment kafkaEnvironment;

    @Bean
    public KafkaTemplate<String, String> stringKafkaTemplate(ProducerFactory<String, String> producerFactory) {
        KafkaTemplate<String, String> kafkaTemplate =  new KafkaTemplate<>(producerFactory);
        kafkaTemplate.setTransactionIdPrefix(kafkaEnvironment.getKAFKA_TOPIC_TRANSACTION_ID());

        return kafkaTemplate;
    }
}
