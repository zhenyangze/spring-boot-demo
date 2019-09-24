package com.xzixi.demo.service.impl;

import com.xzixi.demo.mapper.SummaryMapper;
import com.xzixi.demo.model.po.BroadcastMessage;
import com.xzixi.demo.model.po.Mail;
import com.xzixi.demo.model.vo.SummaryVO;
import com.xzixi.demo.params.Params;
import com.xzixi.demo.service.ISummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummaryService implements ISummaryService {

    @Autowired
    private SummaryMapper summaryMapper;

    @Override
    @Cacheable(cacheNames = {"summary:broadcast:month"}, keyGenerator = "defaultPageKeyGenerator")
    public List<BroadcastMessage> getBroadcastByTime(Params<BroadcastMessage> params) {
        return summaryMapper.selectBroadcastByTime(params);
    }

    @Override
    @Cacheable(cacheNames = {"summary:mail:month"}, keyGenerator = "defaultPageKeyGenerator")
    public List<Mail> getMailByTime(Params<Mail> params) {
        return summaryMapper.selectMailByTime(params);
    }

    @Override
    @Cacheable(cacheNames = {"summary:broadcast:user"}, keyGenerator = "defaultPageKeyGenerator")
    public List<SummaryVO> broadcastGroupBySendUser() {
        return summaryMapper.broadcastGroupBySendUser();
    }

    @Override
    @Cacheable(cacheNames = {"summary:mail:user"}, keyGenerator = "defaultPageKeyGenerator")
    public List<SummaryVO> mailGroupBySendUser() {
        return summaryMapper.mailGroupBySendUser();
    }

    @Override
    @Cacheable(cacheNames = {"summary:user:sex"}, keyGenerator = "defaultPageKeyGenerator")
    public List<SummaryVO> userGroupBySex() {
        return summaryMapper.userGroupBySex();
    }

}
