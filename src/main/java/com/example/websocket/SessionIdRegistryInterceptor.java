package com.example.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.StompSubProtocolHandler;

public class SessionIdRegistryInterceptor implements FromClientInterceptor {

    @Autowired
    private SessionIdRegistry sessionIdRegistry;

    @Override
    public void postHandle(WebSocketSession session, WebSocketMessage<?> webSocketMessage, MessageChannel outputChannel, StompSubProtocolHandler handler) {

    }

}
