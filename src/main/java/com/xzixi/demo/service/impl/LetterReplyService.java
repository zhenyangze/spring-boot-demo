package com.xzixi.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzixi.demo.exception.LogicException;
import com.xzixi.demo.mapper.LetterReplyMapper;
import com.xzixi.demo.model.po.*;
import com.xzixi.demo.params.Params;
import com.xzixi.demo.service.*;
import com.google.common.collect.Lists;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
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
    @Autowired
    private ILetterReplyAttachmentLinkService letterReplyAttachmentLinkService;
    @Autowired
    private IAttachmentService attachmentService;

    @Override
    public IPage<LetterReply> customPage(Page<LetterReply> page, Params<LetterReply> params) {
        return page.setRecords(baseMapper.customSelectPage(page, params));
    }

    @Override
    public LetterReply customGetById(Integer id) {
        return baseMapper.customSelectById(id);
    }

    @Override
    @Transactional
    public void customSave(LetterReply letterReply) {
        User currentUser = currentUser();
        long now = System.currentTimeMillis();
        letterReply.setReplyUserId(currentUser.getId());
        letterReply.setReplyTime(now);
        baseMapper.insert(letterReply);
        Integer letterReplyId = letterReply.getId();
        List<Attachment> attachments = letterReply.getAttachments();
        if (!Collections.isEmpty(attachments)) {
            List<LetterReplyAttachmentLink> links = new ArrayList<>(attachments.size());
            for (Attachment attachment: attachments) {
                Integer attachmentId = attachment.getId();
                Attachment a = attachmentService.customGetById(attachmentId);
                if (a!=null && letterReply.getReplyContent().contains(a.getAttachmentAddress())) {
                    links.add(new LetterReplyAttachmentLink(letterReplyId, attachmentId));
                }
            }
            letterReplyAttachmentLinkService.saveBatch(links);
        }
    }

    @Override
    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = {"attachment:multiple"}, allEntries = true),
                    @CacheEvict(cacheNames = {"attachment:single"}, allEntries = true)
            }
    )
    public void customRemoveByIds(List<Integer> ids) {
        User currentUser = currentUser();
        Integer currentUserId = currentUser.getId();
        Collection<LetterReply> collection = baseMapper.selectBatchIds(ids);
        for (LetterReply reply: collection) {
            if (!currentUserId.equals(reply.getReplyUserId())) {
                throw new LogicException("无法删除其他用户的回复！");
            }
        }
        baseMapper.deleteBatchIds(ids);
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

}
