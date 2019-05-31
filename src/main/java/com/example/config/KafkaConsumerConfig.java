package com.example.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * kafka自动配置只配置了一个消费者，
 * 使用{@link KafkaListener}无法使用配置文件中的值动态指定groupId等参数，
 * 使用此配置类添加自定义的消费者，用来完成个性化需求
 */
@Configuration
@EnableConfigurationProperties(KafkaConsumerConfig.WebsocketConsumerProperties.class)
public class KafkaConsumerConfig {

    @Autowired
    private KafkaProperties kafkaProperties;
    @Autowired
    private WebsocketConsumerProperties websocketConsumerProperties;

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> websocketListenerContainerFactory() {
        Map<String, Object> properties = kafkaProperties.buildConsumerProperties();
        properties.putAll(websocketConsumerProperties.getProperties());
        System.out.println(properties);
        ConsumerFactory<String, String> consumerFactory = new DefaultKafkaConsumerFactory<>(properties);
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(kafkaProperties.getListener().getConcurrency());
        return factory;
    }

    @Data
    @ConfigurationProperties(prefix = "kafka.consumer.websocket")
    public class WebsocketConsumerProperties {
        private final Map<String, String> properties = new HashMap<>();
    }

}
