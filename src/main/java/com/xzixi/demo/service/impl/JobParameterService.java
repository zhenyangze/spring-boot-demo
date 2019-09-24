package com.xzixi.demo.service.impl;

import com.xzixi.demo.mapper.JobParameterMapper;
import com.xzixi.demo.model.po.JobParameter;
import com.xzixi.demo.service.IJobParameterService;
import org.springframework.stereotype.Service;

@Service
public class JobParameterService extends BaseService<JobParameterMapper, JobParameter> implements IJobParameterService {
}
