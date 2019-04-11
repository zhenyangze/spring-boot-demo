package com.example.util;

import com.alibaba.fastjson.JSON;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.support.GenericMessage;

import java.nio.charset.StandardCharsets;

public class GenericMessageBuilder {

    private Object payload;
    private SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);

    public GenericMessageBuilder payload(Object payload) {
        this.payload = payload;
        return this;
    }

    public GenericMessageBuilder destination(String destination) {
        this.headerAccessor.setDestination(destination);
        return this;
    }

    public GenericMessageBuilder leaveMutable(boolean leaveMutable) {
        this.headerAccessor.setLeaveMutable(leaveMutable);
        return this;
    }

    public GenericMessage<byte[]> build() {
        return new GenericMessage<>(JSON.toJSONString(payload).getBytes(StandardCharsets.UTF_8), headerAccessor.getMessageHeaders());
    }

}
