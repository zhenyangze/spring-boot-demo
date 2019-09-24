package com.xzixi.demo.service.impl;

import com.xzixi.demo.mapper.BroadcastToUserLinkMapper;
import com.xzixi.demo.model.po.BroadcastToUserLink;
import com.xzixi.demo.service.IBroadcastToUserLinkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BroadcastToUserLinkService extends BaseService<BroadcastToUserLinkMapper, BroadcastToUserLink> implements IBroadcastToUserLinkService {

    @Override
    @Transactional
    public void customUpdateBatchById(List<BroadcastToUserLink> links) {
        baseMapper.customUpdateBatchById(links);
    }

}
