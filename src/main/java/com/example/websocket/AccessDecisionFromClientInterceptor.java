package com.example.websocket;

import org.springframework.messaging.MessageChannel;
import org.springframework.security.core.Authentication;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.StompSubProtocolHandler;

// websocket消息授权决策
public class AccessDecisionFromClientInterceptor implements FromClientInterceptor {

    @Override
    public boolean preHandle(WebSocketSession session, WebSocketMessage<?> webSocketMessage, MessageChannel outputChannel, StompSubProtocolHandler handler) {
        System.out.println("-----------------websocket消息授权决策-----------------");
        System.out.println("session_id: "+session.getId());
        Authentication authentication = (Authentication) session.getPrincipal();
        if (authentication!=null) {
            // 用户信息
            System.out.println("principal: "+authentication.getPrincipal());
            // 角色信息
            System.out.println("authorities: "+authentication.getAuthorities());
        } else {
            System.out.println("authentication is null");
        }
        System.out.println("message:\n"+webSocketMessage.getPayload());
        return true;
    }

}
