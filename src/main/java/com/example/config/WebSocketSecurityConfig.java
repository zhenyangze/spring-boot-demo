package com.example.config;

import com.example.websocket.AccessDecisionFromClientInterceptor;
import com.example.websocket.CustomizeWebMvcStompEndpointRegistry;
import com.example.websocket.StompSubProtocolErrorHandlerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;

/**
 * 默认通过注解{@link org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker}
 * 开启使用STOMP协议来传输基于代理(message broker)的消息，
 * 支持使用{@link org.springframework.messaging.handler.annotation.MessageMapping}
 * 就像支持{@link org.springframework.web.bind.annotation.RequestMapping}一样。
 * 但是注解{@link org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker}
 * 会引入{@link org.springframework.web.socket.config.annotation.DelegatingWebSocketMessageBrokerConfiguration}配置类，
 * 该配置类默认使用{@link org.springframework.web.socket.config.annotation.WebMvcStompEndpointRegistry}，
 * {@link org.springframework.web.socket.config.annotation.WebMvcStompEndpointRegistry}的stomp协议处理器为
 * {@link org.springframework.web.socket.messaging.StompSubProtocolHandler}，
 * 处理消息的方法：
 * @see org.springframework.web.socket.messaging.StompSubProtocolHandler#handleMessageFromClient(WebSocketSession, WebSocketMessage, MessageChannel)
 * @see org.springframework.web.socket.messaging.StompSubProtocolHandler#handleMessageToClient(WebSocketSession, Message)
 * 未对自定义拦截做支持，
 * 所以取消{@link org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker}，
 * 使用自定义配置{@link com.example.websocket.CustomizeWebSocketMessageBrokerConfiguration}
 * 后续将这部分配置单独做一个项目
 */
@Configuration
//@EnableWebSocketMessageBroker
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    // 异常处理器
    @Bean
    public StompSubProtocolErrorHandler stompSubProtocolErrorHandler() {
        return new StompSubProtocolErrorHandlerImpl();
    }

    // 授权决策拦截器
    @Bean
    public AccessDecisionFromClientInterceptor accessDecisionFromClientInterceptor() {
        return new AccessDecisionFromClientInterceptor();
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/endpoint").withSockJS();
        registry.setErrorHandler(stompSubProtocolErrorHandler());
        // 配置授权决策拦截器
        ((CustomizeWebMvcStompEndpointRegistry) registry).addFromClientInterceptors(accessDecisionFromClientInterceptor());
    }

    // 这里取消所有检查，统一在授权决策拦截器中处理
    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages.anyMessage().permitAll();
    }

    // 关闭同源策略
    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
    }

}
