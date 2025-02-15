package com.example.transaction_service.configuration.kakfa.consumer.factory;

import com.example.transaction_service.configuration.kakfa.environment.KafkaEnvironment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConsumerFactoryConfiguration {
    private final KafkaEnvironment kafkaEnvironment;

    @Bean
    public ObjectMapper objectMapper() {
        return new JsonMapper();
    }

    @Bean
    public ConsumerFactory<String, String> stringConsumerFactory() {
        var kafkaConsumerFactory = new DefaultKafkaConsumerFactory<String, String>(kafkaConsumerProperties());
        kafkaConsumerFactory.setValueDeserializer(new StringDeserializer());

        return kafkaConsumerFactory;
    }

    private Map<String, Object> kafkaConsumerProperties(){
        Map<String, Object> properties = new HashMap<>();

        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaEnvironment.getKAFKA_BOOTSTRAP_SERVERS());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaEnvironment.getKAFKA_CONSUMER_AUTO_OFFSET_RESET());

        return properties;
    }
}
