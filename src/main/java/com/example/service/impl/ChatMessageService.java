package com.example.service.impl;

import com.example.mapper.ChatMessageMapper;
import com.example.model.po.ChatMessage;
import com.example.service.IChatMessageService;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageService extends BaseService<ChatMessageMapper, ChatMessage> implements IChatMessageService {

}
