package com.example.service;

import com.example.model.User;
import com.example.websocket.Message;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface WebSocketService {

    Map<String, User> USER_MAP = new ConcurrentHashMap<>();

    Map<String, Session> SESSION_MAP = new ConcurrentHashMap<>();

    void send(Message message);

}
