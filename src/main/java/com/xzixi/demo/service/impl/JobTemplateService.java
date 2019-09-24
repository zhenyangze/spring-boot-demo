package com.xzixi.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzixi.demo.mapper.JobTemplateMapper;
import com.xzixi.demo.model.po.JobTemplate;
import com.xzixi.demo.model.po.JobTemplateParameter;
import com.xzixi.demo.params.Params;
import com.xzixi.demo.service.IJobTemplateParameterService;
import com.xzixi.demo.service.IJobTemplateService;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobTemplateService extends BaseService<JobTemplateMapper, JobTemplate> implements IJobTemplateService {

    @Autowired
    private IJobTemplateParameterService jobTemplateParameterService;

    @Override
    public List<JobTemplate> customList(Params<JobTemplate> params) {
        return baseMapper.customSelectList(params);
    }

    @Override
    public IPage<JobTemplate> customPage(Page<JobTemplate> page, Params<JobTemplate> params) {
        return page.setRecords(baseMapper.customSelectPage(page, params));
    }

    @Override
    public JobTemplate customGetById(Integer id) {
        return baseMapper.customSelectById(id);
    }

    @Override
    @Transactional
    public void customSave(JobTemplate jobTemplate) {
        long now = System.currentTimeMillis();
        jobTemplate.setCreateTime(now);
        baseMapper.insert(jobTemplate);
        Integer jobTemplateId = jobTemplate.getId();
        List<JobTemplateParameter> parameters = jobTemplate.getParameters();
        if (!Collections.isEmpty(parameters)) {
            for (JobTemplateParameter parameter: parameters) {
                parameter.setJobTemplateId(jobTemplateId);
            }
            jobTemplateParameterService.saveBatch(parameters);
        }
    }

    @Override
    @Transactional
    public void customUpdateById(JobTemplate jobTemplate) {
        baseMapper.updateById(jobTemplate);
        Integer jobTemplateId = jobTemplate.getId();
        List<JobTemplateParameter> parameters = jobTemplate.getParameters();
        if (Collections.isEmpty(parameters)) {
            parameters = new ArrayList<>();
        } else {
            for (JobTemplateParameter parameter: parameters) {
                parameter.setJobTemplateId(jobTemplateId);
            }
        }
        jobTemplateParameterService.merge(parameters, new QueryWrapper<JobTemplateParameter>().eq("job_template_id", jobTemplateId));
    }

}
