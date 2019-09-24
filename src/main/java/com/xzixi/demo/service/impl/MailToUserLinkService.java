package com.xzixi.demo.service.impl;

import com.xzixi.demo.mapper.MailToUserLinkMapper;
import com.xzixi.demo.model.po.MailToUserLink;
import com.xzixi.demo.service.IMailToUserLinkService;
import org.springframework.stereotype.Service;

@Service
public class MailToUserLinkService extends BaseService<MailToUserLinkMapper, MailToUserLink> implements IMailToUserLinkService {
}
