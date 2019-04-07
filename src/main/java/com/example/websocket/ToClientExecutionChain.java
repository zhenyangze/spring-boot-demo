package com.example.websocket;

import org.springframework.messaging.Message;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

public class ToClientExecutionChain {

    private List<ToClientInterceptor> interceptors;

    private int interceptorIndex = -1;

    public ToClientExecutionChain(List<ToClientInterceptor> interceptors) {
        Assert.notNull(interceptors, "interceptors不能为null");
        this.interceptors = interceptors;
    }

    public boolean applyPreHandle(WebSocketSession session, Message<?> message) {
        if (!ObjectUtils.isEmpty(interceptors)) {
            for (int i=0; i<interceptors.size(); i++) {
                ToClientInterceptor interceptor = interceptors.get(i);
                if (!interceptor.preHandle(session, message)) {
                    applyPostHandle(session, message);
                    return false;
                }
                this.interceptorIndex = i;
            }
        }
        return true;
    }

    public void applyPostHandle(WebSocketSession session, Message<?> message) {
        if (!ObjectUtils.isEmpty(interceptors)) {
            for (int i = this.interceptorIndex; i >= 0; i--) {
                ToClientInterceptor interceptor = interceptors.get(i);
                interceptor.postHandle(session, message);
            }
        }
    }

}
