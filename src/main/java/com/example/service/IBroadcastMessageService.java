package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.params.Params;
import com.example.model.po.BroadcastMessage;

public interface IBroadcastMessageService extends IBaseService<BroadcastMessage> {

    IPage<BroadcastMessage> customPage(Page<BroadcastMessage> page, Params<BroadcastMessage> params);

    BroadcastMessage customGetById(Integer id);

}
