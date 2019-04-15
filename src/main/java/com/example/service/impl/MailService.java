package com.example.service.impl;

import com.example.mapper.MailMapper;
import com.example.model.po.Mail;
import com.example.service.IMailService;
import org.springframework.stereotype.Service;

@Service
public class MailService extends BaseService<MailMapper, Mail> implements IMailService {
}
