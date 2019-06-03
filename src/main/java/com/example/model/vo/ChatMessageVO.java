package com.example.model.vo;

import com.example.model.po.ChatMessage;
import lombok.Data;

@Data
public class ChatMessageVO extends ChatMessage {

    private static final long serialVersionUID = 1L;
    private Integer readStatus;

}
