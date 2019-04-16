package com.example.service;

import com.example.model.po.Mail;

public interface IMailService extends IBaseService<Mail> {

    // 草稿
    String DRAFT_STATUS = "draft";

    // 已发送
    String SENT_STATUS = "sent";

    void send(Integer id);

}
