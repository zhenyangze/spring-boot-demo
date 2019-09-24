package com.xzixi.demo.kafka;

import com.xzixi.demo.model.po.BroadcastMessage;
import com.xzixi.demo.model.po.ChatMessage;
import com.xzixi.demo.model.po.User;
import com.xzixi.demo.model.vo.BroadcastMessageVO;
import com.xzixi.demo.model.vo.ChatMessageVO;
import com.xzixi.demo.model.vo.UserVO;
import com.xzixi.demo.util.MessageHeadersBuilder;
import com.xzixi.demo.util.ModelUtil;
import com.xzixi.demo.websocket.SessionIdRegistry;
import io.jsonwebtoken.lang.Collections;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.xzixi.demo.config.KafkaConfig.BROADCAST_TOPIC;
import static com.xzixi.demo.config.KafkaConfig.CHAT_TOPIC;

public class WebsocketConsumer {

    @Value("${websocket.destination.broadcast}")
    private String broadcast;
    @Value("${websocket.destination.chat}")
    private String chat;
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private SessionIdRegistry sessionIdRegistry;

    @KafkaListener(topics = {BROADCAST_TOPIC}, containerFactory = "websocketListenerContainerFactory")
    public void broadcastConsumer(List<BroadcastMessage> broadcastMessages, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, Consumer consumer) {
        if (Collections.isEmpty(broadcastMessages)) {
            return;
        }
        broadcastMessages.forEach(broadcastMessage -> {
            BroadcastMessageVO broadcastMessageVO = (BroadcastMessageVO) ModelUtil.copy(broadcastMessage,
                    new ModelUtil.Mapping(BroadcastMessage.class, BroadcastMessageVO.class, "toUsers"),
                    new ModelUtil.Mapping(User.class, UserVO.class, "password", "roles"));
            Set<String> sessionIds = new HashSet<>();
            broadcastMessage.getToUsers().forEach(user -> sessionIds.addAll(sessionIdRegistry.getSessionIds(user.getId())));
            sessionIds.forEach(sessionId -> template.convertAndSendToUser(
                    sessionId,
                    broadcast,
                    broadcastMessageVO,
                    new MessageHeadersBuilder()
                            .sessionId(sessionId)
                            .leaveMutable(true)
                            .build()));
        });
    }

    @KafkaListener(topics = {CHAT_TOPIC}, containerFactory = "websocketListenerContainerFactory")
    public void chatConsumer(List<ChatMessage> chatMessages, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, Consumer consumer) {
        if (Collections.isEmpty(chatMessages)) {
            return;
        }
        chatMessages.forEach(chatMessage -> {
            Set<String> sessionIds = new HashSet<>();
            sessionIds.addAll(sessionIdRegistry.getSessionIds(chatMessage.getToUserId()));
            sessionIds.addAll(sessionIdRegistry.getSessionIds(chatMessage.getSendUserId()));
            ChatMessageVO chatMessageVO = (ChatMessageVO) ModelUtil.copy(chatMessage,
                    new ModelUtil.Mapping(ChatMessage.class, ChatMessageVO.class),
                    new ModelUtil.Mapping(User.class, UserVO.class, "password", "roles"));
            sessionIds.forEach(sessionId -> template.convertAndSendToUser(
                    sessionId,
                    chat,
                    chatMessageVO,
                    new MessageHeadersBuilder()
                            .sessionId(sessionId)
                            .leaveMutable(true)
                            .build()));
        });
    }

}
