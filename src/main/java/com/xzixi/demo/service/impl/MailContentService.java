package com.xzixi.demo.service.impl;

import com.xzixi.demo.mapper.MailContentMapper;
import com.xzixi.demo.model.po.MailContent;
import com.xzixi.demo.service.IMailContentService;
import org.springframework.stereotype.Service;

@Service
public class MailContentService extends BaseService<MailContentMapper, MailContent> implements IMailContentService {
}
