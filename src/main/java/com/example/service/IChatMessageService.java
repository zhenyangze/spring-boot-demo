package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.params.Params;
import com.example.model.po.ChatMessage;

public interface IChatMessageService extends IBaseService<ChatMessage> {

    IPage<ChatMessage> customPage(Page<ChatMessage> page, Params<ChatMessage> params);

    ChatMessage customGetById(Integer id);

}
