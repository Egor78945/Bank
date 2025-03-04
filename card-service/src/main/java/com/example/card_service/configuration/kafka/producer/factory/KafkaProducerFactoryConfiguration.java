package com.example.card_service.configuration.kafka.producer.factory;

import com.example.card_service.environment.kafka.KafkaEnvironment;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaProducerFactoryConfiguration {
    private final KafkaEnvironment kafkaEnvironment;

    @Bean
    public ProducerFactory<String, String> messageAddressModelProducerFactory() {
        DefaultKafkaProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(producerProperties(kafkaEnvironment.getKAFKA_TOPIC_TRANSACTION_NAME()));
        producerFactory.setValueSerializer(new StringSerializer());

        return producerFactory;
    }

    private Map<String, Object> producerProperties(String transactionId) {
        Map<String, Object> producerProperties = new HashMap<>();

        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaEnvironment.getKAFKA_BOOTSTRAP_SERVERS());
        producerProperties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        producerProperties.put(ProducerConfig.ACKS_CONFIG, "all");
        producerProperties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, transactionId);
        producerProperties.put(ProducerConfig.RETRIES_CONFIG, kafkaEnvironment.getKAFKA_PRODUCER_RETRY_COUNT());

        return producerProperties;
    }
}
