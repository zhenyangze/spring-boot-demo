package com.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.service.IWebSocketService;
import com.example.websocket.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WebSocketService implements IWebSocketService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void send(Message message) {
        Integer fromUserId = message.getFromUserId();
        User from = null;
        if (fromUserId!=null) {
            from = userMapper.selectById(fromUserId);
        }
        Integer toUserId = message.getToUserId();
        List<Session> toSessions = new ArrayList<>();
        User to = null;
        if (toUserId!=null) {
            for (Map.Entry<String, User> entry: USER_MAP.entrySet()) {
                User user = entry.getValue();
                if (toUserId.equals(user.getId())) {
                    toSessions.add(SESSION_MAP.get(entry.getKey()));
                    to = user;
                }
            }
        } else {
            toSessions.addAll(SESSION_MAP.values());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("from", from);
        map.put("to", to);
        map.put("message", message.getMessage());
        String result = JSONObject.toJSONString(map);
        for (Session session: toSessions) {
            session.getAsyncRemote().sendText(result);
        }
    }

}
