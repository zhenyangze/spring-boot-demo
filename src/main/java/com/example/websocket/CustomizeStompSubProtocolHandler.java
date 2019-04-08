package com.example.websocket;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.util.Assert;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.StompSubProtocolHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 代替{@link org.springframework.web.socket.messaging.StompSubProtocolHandler}
 * 增加了对拦截器的支持
 */
public class CustomizeStompSubProtocolHandler extends StompSubProtocolHandler {

    private List<FromClientInterceptor> fromClientInterceptors = new ArrayList<>();

    private List<ToClientInterceptor> toClientInterceptors = new ArrayList<>();

    @Override
    public void handleMessageFromClient(WebSocketSession session, WebSocketMessage<?> webSocketMessage, MessageChannel outputChannel) {
        FromClientExecutionChain chain = new FromClientExecutionChain(fromClientInterceptors);
        if (chain.applyPreHandle(session, webSocketMessage, outputChannel, this)) {
            super.handleMessageFromClient(session, webSocketMessage, outputChannel);
            chain.applyPostHandle(session, webSocketMessage, outputChannel, this);
        }
    }

    @Override
    public void handleMessageToClient(WebSocketSession session, Message<?> message) {
        ToClientExecutionChain chain = new ToClientExecutionChain(toClientInterceptors);
        if (chain.applyPreHandle(session, message, this)) {
            super.handleMessageToClient(session, message);
            chain.applyPostHandle(session, message, this);
        }
    }

    public void addFromClientInterceptor(FromClientInterceptor interceptor) {
        Assert.notNull(interceptor, "interceptor不能为null");
        this.fromClientInterceptors.add(interceptor);
    }

    public void addToClientInterceptor(ToClientInterceptor interceptor) {
        Assert.notNull(interceptor, "interceptor不能为null");
        this.toClientInterceptors.add(interceptor);
    }

}
