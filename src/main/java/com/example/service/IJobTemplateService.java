package com.example.service;

import com.example.model.po.JobTemplate;

public interface IJobTemplateService extends IBaseService<JobTemplate> {

    JobTemplate customGetById(Integer id);

    void customSave(JobTemplate jobTemplate);

    void customUpdateById(JobTemplate jobTemplate);

}
