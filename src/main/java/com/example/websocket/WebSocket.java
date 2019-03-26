package com.example.websocket;

import com.alibaba.fastjson.JSONObject;
import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.service.IWebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/{webSocketId}/{userId}")
@Component
@Slf4j
public class WebSocket {

    private static UserMapper userMapper;
    private static IWebSocketService webSocketService;

    @Autowired
    public void setUserDao(UserMapper userMapper) {
        WebSocket.userMapper = userMapper;
    }
    @Autowired
    public void setWebSocketService(IWebSocketService webSocketService) {
        WebSocket.webSocketService = webSocketService;
    }

    @OnOpen
    public void onOpen(@PathParam("webSocketId") String webSocketId, @PathParam("userId") Integer userId, Session session) {
        User user = userMapper.selectById(userId);
        log.info(user.getUsername()+"加入websocket");
        IWebSocketService.USER_MAP.put(webSocketId, user);
        IWebSocketService.SESSION_MAP.put(webSocketId, session);
    }

    @OnClose
    public void onClose(@PathParam("webSocketId") String webSocketId) {
        log.info(IWebSocketService.USER_MAP.get(webSocketId).getUsername()+"关闭websocket");
        IWebSocketService.SESSION_MAP.remove(webSocketId);
        IWebSocketService.USER_MAP.remove(webSocketId);
    }

    @OnMessage
    public void onMessage(@PathParam("webSocketId") String webSocketId, String message) {
        log.info(IWebSocketService.USER_MAP.get(webSocketId).getUsername()+"说："+message);
        webSocketService.send(JSONObject.parseObject(message, Message.class));
    }

    @OnError
    public void onError(Throwable error) {
        log.error(error.getMessage(), error);
    }

}
