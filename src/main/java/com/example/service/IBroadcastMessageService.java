package com.example.service;

import com.example.model.po.BroadcastMessage;

public interface IBroadcastMessageService extends IBaseService<BroadcastMessage> {

    /** 已读 */
    Integer READ = 1;
    /** 未读 */
    Integer UNREAD = 0;

}
