package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mapper.LetterMapper;
import com.example.model.po.Letter;
import com.example.model.po.Mail;
import com.example.model.po.MailContent;
import com.example.model.po.User;
import com.example.params.Params;
import com.example.service.ILetterService;
import com.example.service.IMailService;
import com.example.service.IUserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class LetterService extends BaseService<LetterMapper, Letter> implements ILetterService {

    @Value("${letter-notify.to-username}")
    private String toUsername;
    @Autowired
    private IUserService userService;
    @Autowired
    private IMailService mailService;

    @Override
    public IPage<Letter> customPage(Page<Letter> page, Params<Letter> params) {
        return page.setRecords(baseMapper.customSelectPage(page, params));
    }

    @Override
    public Letter customGetById(Integer id) {
        return baseMapper.customSelectById(id);
    }

    @Override
    @Transactional
    public Mail notifyMail(Letter letter) {
        User letterUser = userService.getById(letter.getLetterUserId());
        User toUser = userService.getOne(new QueryWrapper<>(new User().setUsername(toUsername)));
        Mail mail = new Mail();
        mail.setMailSubject("REACT中后台留言提醒");
        mail.setMailType("info");
        String content = "";
        content += "<p>您好：</p>";
        content += "<p>收到了来自用户"+letterUser.getNickname()+"的留言。</p>";
        content += "<p>留言摘要："+letter.getDescription()+"</p>";
        content += "<p>请及时查看。</p>";
        content += "<p>----------------------------------</p>";
        content += "<p>REACT中后台</p>";
        content += "<p>"+new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())+"</p>";
        mail.setMailContent(new MailContent().setContent(content));
        mail.setToUsers(Lists.newArrayList(toUser));
        mailService.customSave(mail);
        return mail;
    }

}
