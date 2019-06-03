package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.model.po.BroadcastMessage;
import com.example.params.Params;

public interface IBroadcastMessageService extends IBaseService<BroadcastMessage> {

    /** 已读 */
    Integer READ = 1;
    /** 未读 */
    Integer UNREAD = 0;

    IPage<BroadcastMessage> selfPage(Page<BroadcastMessage> page, Params<BroadcastMessage> params);

    BroadcastMessage getSelfById(Integer id, Params<BroadcastMessage> params);
}
