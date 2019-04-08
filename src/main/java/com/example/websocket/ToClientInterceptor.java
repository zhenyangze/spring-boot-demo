package com.example.websocket;

import org.springframework.messaging.Message;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.StompSubProtocolHandler;

/**
 * websocket消息拦截器，
 * 拦截发送给客户端的消息
 */
public interface ToClientInterceptor {

    /**
     * 前置处理
     * @see CustomizeStompSubProtocolHandler#handleMessageToClient(WebSocketSession, Message)
     * @param session websocket session
     * @param message websocket消息
     * @param handler stomp协议控制器
     * @return true 执行后续操作，false 取消后续操作
     */
    default boolean preHandle(WebSocketSession session, Message<?> message, StompSubProtocolHandler handler) {
        return true;
    }

    /**
     * 后置处理
     * @see CustomizeStompSubProtocolHandler#handleMessageToClient(WebSocketSession, Message)
     * @param session websocket session
     * @param message websocket消息
     * @param handler stomp协议控制器
     */
    default void postHandle(WebSocketSession session, Message<?> message, StompSubProtocolHandler handler) {

    }

}
