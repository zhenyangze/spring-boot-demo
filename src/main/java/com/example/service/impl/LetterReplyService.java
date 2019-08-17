package com.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.exception.LogicException;
import com.example.mapper.LetterReplyMapper;
import com.example.model.po.*;
import com.example.params.Params;
import com.example.service.ILetterReplyService;
import com.example.service.ILetterService;
import com.example.service.IMailService;
import com.example.service.IUserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class LetterReplyService extends BaseService<LetterReplyMapper, LetterReply> implements ILetterReplyService {

    @Autowired
    private ILetterService letterService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IMailService mailService;

    @Override
    public IPage<LetterReply> customPage(Page<LetterReply> page, Params<LetterReply> params) {
        return page.setRecords(baseMapper.customSelectPage(page, params));
    }

    @Override
    @Transactional
    public Mail notifyMail(LetterReply letterReply) {
        Letter letter = letterService.customGetById(letterReply.getLetterId());
        User replyUser = userService.getById(letterReply.getReplyUserId());
        Mail mail = new Mail();
        mail.setMailSubject("REACT中后台留言回复提醒");
        mail.setMailType("info");
        String content = "";
        content += "<p>您好：</p>";
        content += "<p>用户"+replyUser.getNickname()+"回复了您的留言。</p>";
        content += "<p>留言摘要："+letter.getDescription()+"</p>";
        content += "<p>请及时查看。</p>";
        content += "<p>----------------------------------</p>";
        content += "<p>REACT中后台</p>";
        content += "<p>"+new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())+"</p>";
        mail.setMailContent(new MailContent().setContent(content));
        mail.setToUsers(Lists.newArrayList(letter.getLetterUser()));
        mailService.customSave(mail);
        return mail;
    }

    @Override
    public void customRemoveByIds(List<Integer> ids) {
        User currentUser = currentUser();
        Integer currentUserId = currentUser.getId();
        List<LetterReply> list = baseMapper.selectBatchIds(ids);
        for (LetterReply reply: list) {
            if (!currentUserId.equals(reply.getReplyUserId())) {
                throw new LogicException("无法删除其他用户的回复！");
            }
        }
        super.removeByIds(ids);
    }

}
