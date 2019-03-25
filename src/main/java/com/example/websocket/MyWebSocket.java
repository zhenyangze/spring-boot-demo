package com.example.websocket;

import com.alibaba.fastjson.JSONObject;
import com.example.dao.UserDao;
import com.example.model.User;
import com.example.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/{webSocketId}/{userId}")
@Component
@Slf4j
public class MyWebSocket {

    private static UserDao userDao;
    private static WebSocketService webSocketService;

    @Autowired
    public void setUserDao(UserDao userDao) {
        MyWebSocket.userDao = userDao;
    }
    @Autowired
    public void setWebSocketService(WebSocketService webSocketService) {
        MyWebSocket.webSocketService = webSocketService;
    }

    @OnOpen
    public void onOpen(@PathParam("webSocketId") String webSocketId, @PathParam("userId") Integer userId, Session session) {
        User user = userDao.selectById(userId);
        log.info(user.getUsername()+"加入websocket");
        WebSocketService.USER_MAP.put(webSocketId, user);
        WebSocketService.SESSION_MAP.put(webSocketId, session);
    }

    @OnClose
    public void onClose(@PathParam("webSocketId") String webSocketId) {
        log.info(WebSocketService.USER_MAP.get(webSocketId).getUsername()+"关闭websocket");
        WebSocketService.SESSION_MAP.remove(webSocketId);
        WebSocketService.USER_MAP.remove(webSocketId);
    }

    @OnMessage
    public void onMessage(@PathParam("webSocketId") String webSocketId, String message) {
        log.info(WebSocketService.USER_MAP.get(webSocketId).getUsername()+"说："+message);
        webSocketService.send(JSONObject.parseObject(message, Message.class));
    }

    @OnError
    public void onError(Throwable error) {
        log.error(error.getMessage(), error);
    }

}
