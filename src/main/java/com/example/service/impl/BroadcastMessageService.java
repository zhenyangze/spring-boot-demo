package com.example.service.impl;

import com.example.mapper.BroadcastMessageMapper;
import com.example.model.po.BroadcastMessage;
import com.example.service.IBroadcastMessageService;
import org.springframework.stereotype.Service;

@Service
public class BroadcastMessageService extends BaseService<BroadcastMessageMapper, BroadcastMessage> implements IBroadcastMessageService {

}
