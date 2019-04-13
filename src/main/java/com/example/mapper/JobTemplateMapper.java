package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.po.JobTemplate;

public interface JobTemplateMapper extends BaseMapper<JobTemplate> {

    JobTemplate customSelectById(Integer id);

}
