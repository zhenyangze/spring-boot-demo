package com.example.config;

import com.example.kafka.WebsocketConsumer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(KafkaConfig.WebsocketConsumerProperties.class)
@Slf4j
public class KafkaConfig {

    /** 广播消息主题 */
    public static final String BROADCAST_TOPIC = "broadcast";
    /** 点对点消息主题 */
    public static final String CHAT_TOPIC = "chat";
    @Autowired
    private KafkaProperties kafkaProperties;
    @Autowired
    private KafkaConfig.WebsocketConsumerProperties websocketConsumerProperties;

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> websocketListenerContainerFactory() {
        Map<String, Object> properties = kafkaProperties.buildConsumerProperties();
        properties.putAll(websocketConsumerProperties.getProperties());
        System.out.println(properties);
        ConsumerFactory<String, Object> consumerFactory = new DefaultKafkaConsumerFactory<>(properties);
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(kafkaProperties.getListener().getConcurrency());
        return factory;
    }

    @Bean
    public ListenableFutureCallback<SendResult<String, Object>> defaultFutureCallback() {
        return new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onSuccess(SendResult<String, Object> result) {
                log.info("发送消息成功，result: "+result);
            }
            @Override
            public void onFailure(Throwable ex) {
                log.error("发送消息失败，ex: "+ex);
            }
        };
    }

    @Bean
    public WebsocketConsumer websocketConsumer() {
        return new WebsocketConsumer();
    }

    @Data
    @ConfigurationProperties(prefix = "kafka.consumer.websocket")
    public class WebsocketConsumerProperties {
        private final Map<String, String> properties = new HashMap<>();
    }

}
