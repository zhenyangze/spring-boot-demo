package com.example.service;

import com.example.model.po.ChatMessage;

public interface IChatMessageService extends IBaseService<ChatMessage> {

    /** 已读 */
    Integer READ = 1;
    /** 未读 */
    Integer UNREAD = 0;

}
