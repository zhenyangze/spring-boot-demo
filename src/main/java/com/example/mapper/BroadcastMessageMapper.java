package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.params.Params;
import com.example.model.po.BroadcastMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BroadcastMessageMapper extends BaseMapper<BroadcastMessage> {

    // 自定义分页查询
    List<BroadcastMessage> customSelectPage(Page<BroadcastMessage> page, @Param("params") Params<BroadcastMessage> params);

    // 自定义根据id查询
    BroadcastMessage customSelectById(Integer id);

}
