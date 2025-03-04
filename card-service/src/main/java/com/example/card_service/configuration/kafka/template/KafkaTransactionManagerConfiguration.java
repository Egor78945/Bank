package com.example.card_service.configuration.kafka.template;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class KafkaTransactionManagerConfiguration {
    @Bean
    public PlatformTransactionManager stringProducerTransactionManager(ProducerFactory<String, String> producerFactory){
        return new KafkaTransactionManager<>(producerFactory);
    }
}
