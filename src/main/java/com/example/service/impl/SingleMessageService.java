package com.example.service.impl;

import com.example.mapper.SingleMessageMapper;
import com.example.model.po.SingleMessage;
import com.example.service.ISingleMessageService;
import org.springframework.stereotype.Service;

@Service
public class SingleMessageService extends BaseService<SingleMessageMapper, SingleMessage> implements ISingleMessageService {

}
