package com.example.card_service.environment.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaEnvironment {
    private final String KAFKA_CONSUMER_AUTO_OFFSET_RESET;
    private final String KAFKA_TOPIC_TRANSACTION_NAME;
    private final String KAFKA_TOPIC_TRANSACTION_ID;
    private final String KAFKA_BOOTSTRAP_SERVERS;
    private final String KAFKA_PRODUCER_RETRY_COUNT;
    private final int KAFKA_REPLICATION_FACTOR;

    public KafkaEnvironment(@Value("${spring.kafka.consumer.auto-offset-reset}") String KAFKA_CONSUMER_AUTO_OFFSET_RESET, @Value("${spring.kafka.topic.transaction.name}") String KAFKA_TOPIC_TRANSACTION_NAME, @Value("${spring.kafka.topic.transaction.id}") String KAFKA_TOPIC_TRANSACTION_ID, @Value("${spring.kafka.bootstrap-servers}") String KAFKA_BOOTSTRAP_SERVERS, @Value("${spring.kafka.streams.replication-factor}") int KAFKA_REPLICATION_FACTOR, @Value("${KAFKA_PRODUCER_RETRY_COUNT}") String KAFKA_PRODUCER_RETRY_COUNT) {
        this.KAFKA_CONSUMER_AUTO_OFFSET_RESET = KAFKA_CONSUMER_AUTO_OFFSET_RESET;
        this.KAFKA_TOPIC_TRANSACTION_NAME = KAFKA_TOPIC_TRANSACTION_NAME;
        this.KAFKA_TOPIC_TRANSACTION_ID = KAFKA_TOPIC_TRANSACTION_ID;
        this.KAFKA_BOOTSTRAP_SERVERS = KAFKA_BOOTSTRAP_SERVERS;
        this.KAFKA_REPLICATION_FACTOR = KAFKA_REPLICATION_FACTOR;
        this.KAFKA_PRODUCER_RETRY_COUNT = KAFKA_PRODUCER_RETRY_COUNT;
    }

    public String getKAFKA_CONSUMER_AUTO_OFFSET_RESET() {
        return KAFKA_CONSUMER_AUTO_OFFSET_RESET;
    }

    public String getKAFKA_TOPIC_TRANSACTION_NAME() {
        return KAFKA_TOPIC_TRANSACTION_NAME;
    }

    public String getKAFKA_TOPIC_TRANSACTION_ID() {
        return KAFKA_TOPIC_TRANSACTION_ID;
    }

    public String getKAFKA_BOOTSTRAP_SERVERS() {
        return KAFKA_BOOTSTRAP_SERVERS;
    }

    public String getKAFKA_PRODUCER_RETRY_COUNT() {
        return KAFKA_PRODUCER_RETRY_COUNT;
    }

    public int getKAFKA_REPLICATION_FACTOR() {
        return KAFKA_REPLICATION_FACTOR;
    }
}
