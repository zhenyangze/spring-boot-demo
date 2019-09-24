package com.xzixi.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzixi.demo.mapper.OpenSourceMapper;
import com.xzixi.demo.model.po.*;
import com.xzixi.demo.params.Params;
import com.xzixi.demo.service.IAttachmentService;
import com.xzixi.demo.service.IOpenSourceAttachmentLinkService;
import com.xzixi.demo.service.IOpenSourceIntroService;
import com.xzixi.demo.service.IOpenSourceService;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpenSourceService extends BaseService<OpenSourceMapper, OpenSource> implements IOpenSourceService {

    @Autowired
    private IOpenSourceIntroService openSourceIntroService;
    @Autowired
    private IOpenSourceAttachmentLinkService openSourceAttachmentLinkService;
    @Autowired
    private IAttachmentService attachmentService;

    @Override
    public IPage<OpenSource> customPage(Page<OpenSource> page, Params<OpenSource> params) {
        return page.setRecords(baseMapper.customSelectPage(page, params));
    }

    @Override
    public OpenSource customGetById(Integer id) {
        return baseMapper.customSelectById(id);
    }

    @Override
    @Transactional
    public void customSave(OpenSource openSource) {
        User currentUser = currentUser();
        long now = System.currentTimeMillis();
        openSource.setSaveUserId(currentUser.getId());
        openSource.setSaveTime(now);
        openSource.setUpdateUserId(currentUser.getId());
        openSource.setUpdateTime(now);
        baseMapper.insert(openSource);
        Integer openSourceId = openSource.getId();
        OpenSourceIntro intro = openSource.getIntro();
        intro.setOpenSourceId(openSourceId);
        openSourceIntroService.save(intro);
        List<Attachment> attachments = openSource.getAttachments();
        if (!Collections.isEmpty(attachments)) {
            List<OpenSourceAttachmentLink> links = new ArrayList<>(attachments.size());
            for (Attachment attachment : attachments) {
                Integer attachmentId = attachment.getId();
                Attachment a = attachmentService.customGetById(attachmentId);
                if (a!=null && intro.getReadme().contains(a.getAttachmentAddress())) {
                    links.add(new OpenSourceAttachmentLink(openSourceId, attachmentId));
                }
            }
            openSourceAttachmentLinkService.saveBatch(links);
        }
    }

    @Override
    @Transactional
    public void customUpdateById(OpenSource openSource) {
        User currentUser = currentUser();
        long now = System.currentTimeMillis();
        openSource.setUpdateUserId(currentUser.getId());
        openSource.setUpdateTime(now);
        baseMapper.updateById(openSource);
        Integer openSourceId = openSource.getId();
        OpenSourceIntro intro = openSource.getIntro();
        intro.setOpenSourceId(openSourceId);
        openSourceIntroService.updateById(intro);
        List<Attachment> attachments = openSource.getAttachments();
        List<OpenSourceAttachmentLink> links = new ArrayList<>();
        if (!Collections.isEmpty(attachments)) {
            for (Attachment attachment: attachments) {
                Integer attachmentId = attachment.getId();
                Attachment a = attachmentService.customGetById(attachmentId);
                if (a!=null && intro.getReadme().contains(a.getAttachmentAddress())) {
                    links.add(new OpenSourceAttachmentLink(openSourceId, attachmentId));
                }
            }
            openSourceAttachmentLinkService.merge(links, new QueryWrapper<OpenSourceAttachmentLink>().eq("open_source_id", openSourceId));
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
        baseMapper.deleteBatchIds(ids);
    }

}
