package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mapper.params.Params;
import com.example.model.po.ChatMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChatMessageMapper extends BaseMapper<ChatMessage> {

    // 自定义列表查询
    List<ChatMessage> customSelectPage(Page<ChatMessage> page, @Param("params") Params<ChatMessage> params);

    // 自定义根据id查询
    ChatMessage customSelectById(Integer id);

}
