package com.example.service.impl;

import com.example.mapper.SummaryMapper;
import com.example.model.po.BroadcastMessage;
import com.example.model.po.Mail;
import com.example.model.vo.SummaryVO;
import com.example.params.Params;
import com.example.service.ISummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummaryService implements ISummaryService {

    @Autowired
    private SummaryMapper summaryMapper;

    @Override
    public List<BroadcastMessage> getBroadcastByTime(Params<BroadcastMessage> params) {
        return summaryMapper.selectBroadcastByTime(params);
    }

    @Override
    public List<Mail> getMailByTime(Params<Mail> params) {
        return summaryMapper.selectMailByTime(params);
    }

    @Override
    public List<SummaryVO> broadcastGroupBySendUser() {
        return summaryMapper.broadcastGroupBySendUser();
    }

    @Override
    public List<SummaryVO> mailGroupBySendUser() {
        return summaryMapper.mailGroupBySendUser();
    }

    @Override
    public List<SummaryVO> userGroupBySex() {
        return summaryMapper.userGroupBySex();
    }

}
