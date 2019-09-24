package com.xzixi.demo.service.impl;

import com.xzixi.demo.mapper.JobTemplateParameterMapper;
import com.xzixi.demo.model.po.JobTemplateParameter;
import com.xzixi.demo.service.IJobTemplateParameterService;
import org.springframework.stereotype.Service;

@Service
public class JobTemplateParameterService extends BaseService<JobTemplateParameterMapper, JobTemplateParameter> implements IJobTemplateParameterService {
}
