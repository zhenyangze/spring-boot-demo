package com.example.controller;

import com.example.job.TestCronJob;
import com.example.job.TestJob;
import com.example.model.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

import static com.example.model.vo.ResultVO.SUCCESS;

@RestController
@RequestMapping(value = "/job", produces = "application/json; charset=UTF-8")
@Api(tags = "定时任务")
@Validated
public class JobController {

    @Autowired
    private Scheduler scheduler;

    @PostMapping("/testJob")
    @ApiOperation("创建testJob")
    public ResultVO saveTestJob() throws SchedulerException {
        //设置开始时间为1分钟后
        long startAtTime = System.currentTimeMillis() + 1000 * 60;
        //任务名称
        String name = "testJob-"+UUID.randomUUID().toString();
        //任务所属分组
        String group = TestJob.class.getName();
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(TestJob.class).withIdentity(name, group).build();
        jobDetail.getJobDataMap().put("name", "testJob");
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).startAt(new Date(startAtTime)).build();
        //将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
        return new ResultVO<>(SUCCESS, "", null);
    }

    @PostMapping("/testCronJob")
    @ApiOperation("创建testCronJob")
    public ResultVO saveTestCronJob() throws SchedulerException {
        //任务名称
        String name = "testCronJob-"+UUID.randomUUID().toString();
        //任务所属分组
        String group = TestCronJob.class.getName();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/30 * * * * ?");
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(TestCronJob.class).withIdentity(name, group).build();
        jobDetail.getJobDataMap().put("name", "testCronJob");
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(scheduleBuilder).build();
        //将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
        return new ResultVO<>(SUCCESS, "", null);
    }

}
