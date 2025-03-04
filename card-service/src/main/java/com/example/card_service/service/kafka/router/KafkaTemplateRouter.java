package com.example.card_service.service.kafka.router;

import com.example.card_service.model.kafka.template.KafkaTemplateSender;
import com.example.card_service.model.kafka.template.StringKafkaTemplateSender;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class KafkaTemplateRouter {
    private final Map<Class<?>, KafkaTemplateSender> map;

    public KafkaTemplateRouter(StringKafkaTemplateSender stringKafkaTemplateSender) {
        map = new HashMap<>();
        map.put(String.class, stringKafkaTemplateSender);
    }

    public void send(String topic, String key, Object value){
        map.get(value.getClass()).send(topic, key, value);
    }
}
