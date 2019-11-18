package com.xzixi.demo.rabbitmq;

import com.xzixi.demo.config.RabbitmqConfig;
import com.xzixi.demo.model.po.BroadcastMessage;
import com.xzixi.demo.model.po.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;

public class WebsocketMessageSender {

    @Autowired
    private RabbitTemplateAdapter rabbitTemplateAdapter;

    public void sendBroadcast(BroadcastMessage message) {
        rabbitTemplateAdapter.send(RabbitmqConfig.BROADCAST_MESSAGE_EXCHANGE, "", message);
    }

    public void sendChat(ChatMessage message) {
        rabbitTemplateAdapter.send(RabbitmqConfig.CHAT_MESSAGE_EXCHANGE, "", message);
    }

}
