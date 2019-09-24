package com.xzixi.demo.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzixi.demo.model.po.BroadcastMessage;
import com.xzixi.demo.params.Params;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BroadcastMessageMapper extends IBaseMapper<BroadcastMessage> {

    List<BroadcastMessage> selectSelfList(@Param("params") Params<BroadcastMessage> params);

    List<BroadcastMessage> selectSelfPage(Page<BroadcastMessage> page, @Param("params") Params<BroadcastMessage> params);

    BroadcastMessage selectSelfById(Integer id, @Param("params") Params<BroadcastMessage> params);

}
