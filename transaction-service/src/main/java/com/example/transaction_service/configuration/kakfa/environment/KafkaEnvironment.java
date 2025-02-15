package com.example.transaction_service.configuration.kakfa.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaEnvironment {
    private final String KAFKA_CONSUMER_GROUP_ID;
    private final String KAFKA_CONSUMER_AUTO_OFFSET_RESET;
    private final String KAFKA_TOPIC_TRANSACTION;
    private final String KAFKA_BOOTSTRAP_SERVERS;
    private final int KAFKA_REPLICATION_FACTOR;

    public KafkaEnvironment(@Value("${spring.kafka.consumer.group-id}") String KAFKA_CONSUMER_GROUP_ID, @Value("${spring.kafka.consumer.auto-offset-reset}") String KAFKA_CONSUMER_AUTO_OFFSET_RESET, @Value("${spring.kafka.topic.transaction}") String KAFKA_TOPIC_TRANSACTION, @Value("${spring.kafka.bootstrap-servers}") String KAFKA_BOOTSTRAP_SERVERS, @Value("${spring.kafka.streams.replication-factor}") int KAFKA_REPLICATION_FACTOR) {
        this.KAFKA_CONSUMER_GROUP_ID = KAFKA_CONSUMER_GROUP_ID;
        this.KAFKA_CONSUMER_AUTO_OFFSET_RESET = KAFKA_CONSUMER_AUTO_OFFSET_RESET;
        this.KAFKA_TOPIC_TRANSACTION = KAFKA_TOPIC_TRANSACTION;
        this.KAFKA_BOOTSTRAP_SERVERS = KAFKA_BOOTSTRAP_SERVERS;
        this.KAFKA_REPLICATION_FACTOR = KAFKA_REPLICATION_FACTOR;
    }

    public String getKAFKA_CONSUMER_GROUP_ID() {
        return KAFKA_CONSUMER_GROUP_ID;
    }

    public String getKAFKA_CONSUMER_AUTO_OFFSET_RESET() {
        return KAFKA_CONSUMER_AUTO_OFFSET_RESET;
    }

    public String getKAFKA_TOPIC_TRANSACTION() {
        return KAFKA_TOPIC_TRANSACTION;
    }

    public String getKAFKA_BOOTSTRAP_SERVERS() {
        return KAFKA_BOOTSTRAP_SERVERS;
    }

    public int getKAFKA_REPLICATION_FACTOR() {
        return KAFKA_REPLICATION_FACTOR;
    }
}
