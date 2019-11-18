package com.xzixi.demo.rabbitmq;

import com.xzixi.demo.model.po.BroadcastMessage;
import com.xzixi.demo.model.po.ChatMessage;
import com.xzixi.demo.model.po.User;
import com.xzixi.demo.model.vo.BroadcastMessageVO;
import com.xzixi.demo.model.vo.ChatMessageVO;
import com.xzixi.demo.model.vo.UserVO;
import com.xzixi.demo.util.MessageHeadersBuilder;
import com.xzixi.demo.util.ModelUtil;
import com.xzixi.demo.websocket.SessionIdRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.HashSet;
import java.util.Set;

public class WebsocketMessageListener {

    @Value("${websocket.destination.broadcast}")
    private String broadcast;
    @Value("${websocket.destination.chat}")
    private String chat;
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private SessionIdRegistry sessionIdRegistry;

    public void handleBroadcast(BroadcastMessage broadcastMessage) {
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
    }

    public void handleChat(ChatMessage chatMessage) {
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
    }

}
