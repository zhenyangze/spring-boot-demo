package com.xzixi.demo.service;

import com.xzixi.demo.model.po.BroadcastMessage;
import com.xzixi.demo.model.po.Mail;
import com.xzixi.demo.model.vo.SummaryVO;
import com.xzixi.demo.params.Params;

import java.util.List;

public interface ISummaryService {

    List<BroadcastMessage> getBroadcastByTime(Params<BroadcastMessage> params);

    List<Mail> getMailByTime(Params<Mail> params);

    List<SummaryVO> broadcastGroupBySendUser();

    List<SummaryVO> mailGroupBySendUser();

    List<SummaryVO> userGroupBySex();

}
