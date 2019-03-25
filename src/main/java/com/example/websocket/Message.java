package com.example.websocket;

import lombok.Data;

@Data
public class Message {

    private Integer fromUserId;
    private Integer toUserId;
    private String message;

}
