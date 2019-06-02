package com.example.kafka;

import io.jsonwebtoken.lang.Collections;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;

import static com.example.config.KafkaConfig.BROADCAST_TOPIC;
import static com.example.config.KafkaConfig.CHAT_TOPIC;

public class WebsocketConsumer {

    @KafkaListener(topics = {BROADCAST_TOPIC}, containerFactory = "websocketListenerContainerFactory")
    public void broadcastConsumer(List<ConsumerRecord<?, ?>> records) {
        if (Collections.isEmpty(records)) {
            return;
        }
        records.forEach(record -> {
            System.out.println(BROADCAST_TOPIC);
            System.out.println(record);
        });
    }

    @KafkaListener(topics = {CHAT_TOPIC}, containerFactory = "websocketListenerContainerFactory")
    public void chatConsumer(List<ConsumerRecord<?, ?>> records) {
        if (Collections.isEmpty(records)) {
            return;
        }
        records.forEach(record -> {
            System.out.println(CHAT_TOPIC);
            System.out.println(record);
        });
    }

}
