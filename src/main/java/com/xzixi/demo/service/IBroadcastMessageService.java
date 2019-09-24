package com.xzixi.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzixi.demo.model.po.BroadcastMessage;
import com.xzixi.demo.params.Params;

import java.util.List;

public interface IBroadcastMessageService extends IBaseService<BroadcastMessage> {

    /** 已读 */
    Integer READ = 1;
    /** 未读 */
    Integer UNREAD = 0;

    List<BroadcastMessage> selfList(Params<BroadcastMessage> params);

    IPage<BroadcastMessage> selfPage(Page<BroadcastMessage> page, Params<BroadcastMessage> params);

    BroadcastMessage getSelfById(Integer id, Params<BroadcastMessage> params);
}
