package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.exception.LogicException;
import com.example.mapper.LetterMapper;
import com.example.model.po.*;
import com.example.params.Params;
import com.example.service.*;
import com.google.common.collect.Lists;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class LetterService extends BaseService<LetterMapper, Letter> implements ILetterService {

    @Value("${letter-notify.to-username}")
    private String toUsername;
    @Autowired
    private IUserService userService;
    @Autowired
    private IMailService mailService;
    @Autowired
    private ILetterAttachmentLinkService letterAttachmentLinkService;
    @Autowired
    private IAttachmentService attachmentService;

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
    public void customSave(Letter letter) {
        User currentUser = currentUser();
        long now = System.currentTimeMillis();
        letter.setLetterUserId(currentUser.getId());
        letter.setLetterTime(now);
        baseMapper.insert(letter);
        Integer letterId = letter.getId();
        List<Attachment> attachments = letter.getAttachments();
        if (!Collections.isEmpty(attachments)) {
            List<LetterAttachmentLink> links = new ArrayList<>(attachments.size());
            for (Attachment attachment : attachments) {
                Integer attachmentId = attachment.getId();
                Attachment a = attachmentService.customGetById(attachmentId);
                if (a!=null && letter.getLetterContent().contains(a.getAttachmentAddress())) {
                    links.add(new LetterAttachmentLink(letterId, attachmentId));
                }
            }
            letterAttachmentLinkService.saveBatch(links);
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
        Collection<Letter> collection = baseMapper.selectBatchIds(ids);
        for (Letter letter: collection) {
            if (!currentUserId.equals(letter.getLetterUserId())) {
                throw new LogicException("无法删除其他用户的留言！");
            }
        }
        baseMapper.deleteBatchIds(ids);
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

    @Override
    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = {"attachment:multiple"}, allEntries = true),
                    @CacheEvict(cacheNames = {"attachment:single"}, allEntries = true)
            }
    )
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

}
