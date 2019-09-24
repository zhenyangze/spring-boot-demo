package com.xzixi.demo.mapper;

import com.xzixi.demo.model.po.ChatMessage;
import com.xzixi.demo.params.Params;
import org.apache.ibatis.annotations.Param;

public interface ChatMessageMapper extends IBaseMapper<ChatMessage> {

    ChatMessage selectSelfById(Integer id, @Param("params") Params<ChatMessage> params);

}
