package com.example.websocket;

import com.google.common.collect.Maps;
import org.springframework.messaging.MessageChannel;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.StompSubProtocolHandler;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

public class FromClientExecutionChain {

    private List<FromClientInterceptor> interceptors;

    private int interceptorIndex = -1;

    private static Map<WebSocketMessage, MessageFromClient> messageMap;

    static {
        messageMap = Maps.newConcurrentMap();
    }

    public FromClientExecutionChain(List<FromClientInterceptor> interceptors) {
        Assert.notNull(interceptors, "interceptors不能为null");
        this.interceptors = interceptors;
    }

    public boolean applyPreHandle(WebSocketSession session, WebSocketMessage<?> webSocketMessage, MessageChannel outputChannel, StompSubProtocolHandler handler) {
        if (!ObjectUtils.isEmpty(interceptors)) {
            MessageFromClient message = getMessage(webSocketMessage);
            messageMap.put(webSocketMessage, message);
            for (int i=0; i<interceptors.size(); i++) {
                FromClientInterceptor interceptor = interceptors.get(i);
                if (!interceptor.preHandle(session, message, outputChannel, handler)) {
                    applyPostHandle(session, webSocketMessage, outputChannel, handler);
                    return false;
                }
                this.interceptorIndex = i;
            }
        }
        return true;
    }

    public void applyPostHandle(WebSocketSession session, WebSocketMessage<?> webSocketMessage, MessageChannel outputChannel, StompSubProtocolHandler handler) {
        if (!ObjectUtils.isEmpty(interceptors)) {
            MessageFromClient message = messageMap.get(webSocketMessage);
            for (int i = this.interceptorIndex; i >= 0; i--) {
                FromClientInterceptor interceptor = interceptors.get(i);
                interceptor.postHandle(session, message, outputChannel, handler);
            }
            messageMap.remove(webSocketMessage);
        }
    }

    private MessageFromClient getMessage(WebSocketMessage<?> webSocketMessage) {
        if (webSocketMessage instanceof TextMessage) {
            MessageFromClient message = new MessageFromClient();
            String payload = ((TextMessage) webSocketMessage).getPayload();
            String[] arr = payload.split("\n");
            Queue<String> queue = new LinkedTransferQueue<>();
            for (String str: arr) {
                String strTrim = str.trim();
                if (StringUtils.isEmpty(strTrim)) {
                    continue;
                }
                queue.offer(strTrim);
            }
            String type = queue.poll();
            message.setType(type);
            int last = 0;
            if ("SEND".equals(type)) {
                last = 1;
            }
            while (queue.size()>last) {
                String param = queue.poll();
                if (param.startsWith("id:")) {
                    message.setSubId(param.split(":")[1].trim());
                } else if (param.startsWith("destination:")) {
                    message.setDestination(param.split(":")[1].trim());
                } else if (param.startsWith("content-length:")) {
                    message.setContentLength(Integer.valueOf(param.split(":")[1].trim()));
                }
            }
            String content = queue.poll();
            if (!StringUtils.isEmpty(content)) {
                message.setContent(content.trim());
            }
            return message;
        }
        return null;
    }

}
