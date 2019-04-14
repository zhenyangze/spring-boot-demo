package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.model.po.Job;
import com.example.params.Params;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobMapper extends BaseMapper<Job> {

    List<Job> customSelectPage(Page<Job> page, @Param("params") Params params);

    Job customSelectById(Integer id);

}
