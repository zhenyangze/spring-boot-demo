package com.example.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.StompSubProtocolHandler;

public class SessionIdRegistryInterceptor implements FromClientInterceptor {

    @Autowired
    private SessionIdRegistry sessionIdRegistry;

    @Override
    public void postHandle(WebSocketSession session, MessageFromClient message, MessageChannel outputChannel, StompSubProtocolHandler handler) {
//        Authentication authentication = (Authentication) session.getPrincipal();
//        if (authentication!=null) {
//            // 用户信息
//            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//            System.out.println("userDetails: "+userDetails);
//            // 资源信息
//            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//            System.out.println("authorities: "+authorities);
//        } else {
//            System.out.println("authentication is null");
//        }
        // TODO 登记换成ToClientInterceptor，取type为CONNECT_ACK的消息，移除不变
        String type = message.getType();
        if ("CONNECT".equals(type)) {
            System.out.println("CONNECT");
        } else if ("DISCONNECT".equals(type)) {
            System.out.println("DISCONNECT");
        }
    }

}
