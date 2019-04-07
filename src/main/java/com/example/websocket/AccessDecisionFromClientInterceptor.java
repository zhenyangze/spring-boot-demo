package com.example.websocket;

import org.springframework.messaging.MessageChannel;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

// websocket消息授权决策
public class AccessDecisionFromClientInterceptor implements FromClientInterceptor {

    @Override
    public boolean preHandle(WebSocketSession session, WebSocketMessage<?> webSocketMessage, MessageChannel outputChannel) {
        System.out.println("-----------------websocket消息授权决策-----------------");
        System.out.println("id: "+session.getId());
        System.out.println("uri: "+session.getUri());
        System.out.println("message:\n"+webSocketMessage.getPayload());
        return true;
    }

}
