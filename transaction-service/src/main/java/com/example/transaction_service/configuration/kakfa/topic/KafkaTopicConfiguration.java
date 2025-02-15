package com.example.transaction_service.configuration.kakfa.topic;

import com.example.transaction_service.configuration.kakfa.environment.KafkaEnvironment;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@RequiredArgsConstructor
public class KafkaTopicConfiguration {
    private final KafkaEnvironment kafkaEnvironment;

    @Bean
    public NewTopic transactionTopic(){
        return TopicBuilder
                .name(kafkaEnvironment.getKAFKA_TOPIC_TRANSACTION())
                .replicas(kafkaEnvironment.getKAFKA_REPLICATION_FACTOR())
                .partitions(kafkaEnvironment.getKAFKA_REPLICATION_FACTOR())
                .build();
    }
}
