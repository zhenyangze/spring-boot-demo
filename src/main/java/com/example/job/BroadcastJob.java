package com.example.job;

import com.example.kafka.DefaultProducer;
import com.example.model.po.BroadcastMessage;
import com.example.model.po.User;
import com.example.params.Params;
import com.example.service.IBroadcastMessageService;
import com.example.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.example.config.KafkaConfig.BROADCAST_TOPIC;

@Slf4j
public class BroadcastJob extends QuartzJobBean {

    @Autowired
    private IBroadcastMessageService broadcastMessageService;
    @Autowired
    private DefaultProducer defaultProducer;
    @Autowired
    private IUserService userService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("----------broadcastJob开始了----------");
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String username = jobDataMap.getString("senduser");
        User sendUser = userService.customGetOne(new Params<>(new User().setUsername(username)));
        if (sendUser==null) {
            log.info("----------broadcastJob出错了，找不到用户："+username+"----------");
            return;
        }
        BroadcastMessage message = new BroadcastMessage();
        message.setSendUser(sendUser);
        message.setSendUserId(sendUser.getId());
        String content = jobDataMap.getString("content");
        content += "---------现在时间是：";
        content += new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        content += "，react中后台定时向您问好！";
        message.setContent(content);
        List<User> list = userService.customList(new Params<>(new User()));
        message.setToUsers(list);
        broadcastMessageService.customSave(message);
        defaultProducer.send(BROADCAST_TOPIC, message);
        log.info("----------broadcastJob结束了----------");
    }

}
