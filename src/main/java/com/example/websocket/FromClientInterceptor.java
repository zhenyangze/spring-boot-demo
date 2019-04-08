package com.example.websocket;

import org.springframework.messaging.MessageChannel;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.StompSubProtocolHandler;

/**
 * websocket消息拦截器，
 * 拦截客户端发来的消息
 */
public interface FromClientInterceptor {

    /**
     * 前置处理
     * @see CustomizeStompSubProtocolHandler#handleMessageFromClient(WebSocketSession, WebSocketMessage, MessageChannel)
     * @param session websocket session
     * @param webSocketMessage websocket消息
     * @param outputChannel websocket消息通道
     * @param handler stomp协议控制器
     * @return true 执行后续操作，false 取消后续操作
     */
    default boolean preHandle(WebSocketSession session, WebSocketMessage<?> webSocketMessage, MessageChannel outputChannel, StompSubProtocolHandler handler) {
        return true;
    }

    /**
     * 后置处理
     * @see CustomizeStompSubProtocolHandler#handleMessageFromClient(WebSocketSession, WebSocketMessage, MessageChannel)
     * @param session websocket session
     * @param webSocketMessage websocket消息
     * @param outputChannel websocket消息通道
     * @param handler stomp协议控制器
     */
    default void postHandle(WebSocketSession session, WebSocketMessage<?> webSocketMessage, MessageChannel outputChannel, StompSubProtocolHandler handler) {

    }

}
