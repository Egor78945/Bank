package com.example.card_service.service.kafka;

import com.example.card_service.service.kafka.router.KafkaTemplateRouter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaService {
    private final KafkaTemplateRouter kafkaTemplateRouter;

    public void send(String topic, String key, Object value){
        kafkaTemplateRouter.send(topic, key, value);
    }
}
