package com.example.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Slf4j
public class TestJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("----------testJob开始了----------");
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        for (String key: jobDataMap.keySet()) {
            log.info(key + "=" + jobDataMap.get(key));
        }
        log.info("----------testJob结束了----------");
    }

}
