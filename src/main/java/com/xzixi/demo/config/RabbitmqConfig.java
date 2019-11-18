package com.xzixi.demo.config;

import com.xzixi.demo.rabbitmq.RabbitTemplateAdapter;
import com.xzixi.demo.rabbitmq.WebsocketMessageListener;
import com.xzixi.demo.rabbitmq.WebsocketMessageSender;
import com.xzixi.demo.util.SerializeUtil;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitmqConfig {

    public static final String BROADCAST_MESSAGE_EXCHANGE = "broadcast";
    public static final String CHAT_MESSAGE_EXCHANGE = "chat";
    @Value("${rabbitmq.queue.websocket.broadcast}")
    private String broadcastQueueName;
    @Value("${rabbitmq.queue.websocket.chat}")
    private String chatQueueName;

    // 广播消息交换器
    @Bean
    public FanoutExchange broadcastExchange() {
        return new FanoutExchange(BROADCAST_MESSAGE_EXCHANGE);
    }

    // 点对点消息交换器
    @Bean
    public FanoutExchange chatExchange() {
        return new FanoutExchange(CHAT_MESSAGE_EXCHANGE);
    }

    // 广播消息队列
    @Bean
    public Queue broadcastQueue() {
        return new Queue(broadcastQueueName);
    }

    // 点对点消息队列
    @Bean
    public Queue chatQueue() {
        return new Queue(chatQueueName);
    }

    // 广播消息队列绑定到交换器
    @Bean
    public Binding broadcastBinding(FanoutExchange broadcastExchange, Queue broadcastQueue) {
        return BindingBuilder.bind(broadcastQueue).to(broadcastExchange);
    }

    // 点对点消息队列绑定到交换器
    @Bean
    public Binding chatBinding(FanoutExchange chatExchange, Queue chatQueue) {
        return BindingBuilder.bind(chatQueue).to(chatExchange);
    }

    // websocket消息监听器
    @Bean
    public WebsocketMessageListener websocketMessageListener() {
        return new WebsocketMessageListener();
    }

    // mq消息转换器
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter(SerializeUtil.objectMapper);
    }

    // websocket消息监听适配器
    @Bean
    public MessageListener websocketMessageListenerAdapter(WebsocketMessageListener websocketMessageListener,
                                                           MessageConverter messageConverter) {
        MessageListenerAdapter adapter = new MessageListenerAdapter(websocketMessageListener, messageConverter);
        Map<String, String> queueOrTagToMethodName = new HashMap<>(2);
        queueOrTagToMethodName.put(broadcastQueueName, "handleBroadcast");
        queueOrTagToMethodName.put(chatQueueName, "handleChat");
        adapter.setQueueOrTagToMethodName(queueOrTagToMethodName);
        return adapter;
    }

    // websocket消息监听配置
    @Bean
    public SimpleMessageListenerContainer websocketMessageListenerContainer(ConnectionFactory connectionFactory,
                                                                            MessageListener websocketMessageListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(broadcastQueueName, chatQueueName);
        container.setMessageListener(websocketMessageListenerAdapter);
        return container;
    }

    // RabbitTemplate适配器
    @Bean
    public RabbitTemplateAdapter rabbitTemplateAdapter(RabbitTemplate rabbitTemplate) {
        return new RabbitTemplateAdapter(rabbitTemplate);
    }

    // websocket消息发送bean
    @Bean
    public WebsocketMessageSender websocketMessageSender() {
        return new WebsocketMessageSender();
    }

}
