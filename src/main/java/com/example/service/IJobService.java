package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.model.po.Job;
import com.example.params.Params;

public interface IJobService extends IBaseService<Job> {

    // 任务名称和分组前缀
    String JOB_PREFIX = "job-";

    // 触发器名称和分组前缀
    String TRIGGER_PREFIX = "trigger-";

    IPage<Job> customPage(Page<Job> page, Params<Job> params);

    Job customGetById(Integer id);

    void customSave(Job job);

    void customUpdateById(Job job);

    void customRemoveById(Integer id);

    // 暂停触发器
    void pauseTrigger(Integer id);

    // 取消暂停触发器
    void resumeTrigger(Integer id);

}
