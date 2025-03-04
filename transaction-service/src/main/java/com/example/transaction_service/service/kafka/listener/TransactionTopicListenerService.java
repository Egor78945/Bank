package com.example.transaction_service.service.kafka.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionTopicListenerService {

    @KafkaListener(topics = "${spring.kafka.topic.transaction.name}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "stringListenerContainerFactory")
    public void transactionTopicListener(String transaction){
        System.out.println(transaction);
    }
}
