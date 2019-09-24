package com.xzixi.demo.mapper;

import com.xzixi.demo.model.po.BroadcastMessage;
import com.xzixi.demo.model.po.Mail;
import com.xzixi.demo.model.vo.SummaryVO;
import com.xzixi.demo.params.Params;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SummaryMapper {

    List<BroadcastMessage> selectBroadcastByTime(@Param("params") Params<BroadcastMessage> params);

    List<Mail> selectMailByTime(@Param("params") Params<Mail> params);

    List<SummaryVO> broadcastGroupBySendUser();

    List<SummaryVO> mailGroupBySendUser();

    List<SummaryVO> userGroupBySex();

}
