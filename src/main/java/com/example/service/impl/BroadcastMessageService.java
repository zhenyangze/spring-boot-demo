package com.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mapper.BroadcastMessageMapper;
import com.example.model.po.BroadcastMessage;
import com.example.params.Params;
import com.example.service.IBroadcastMessageService;
import org.springframework.stereotype.Service;

@Service
public class BroadcastMessageService extends BaseService<BroadcastMessageMapper, BroadcastMessage> implements IBroadcastMessageService {

    @Override
    public IPage<BroadcastMessage> customPage(Page<BroadcastMessage> page, Params<BroadcastMessage> params) {
        return page.setRecords(baseMapper.customSelectPage(page, params));
    }

    @Override
    public BroadcastMessage customGetById(Integer id) {
        return baseMapper.customSelectById(id);
    }

}
