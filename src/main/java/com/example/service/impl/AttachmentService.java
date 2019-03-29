package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.AttachmentMapper;
import com.example.model.po.Attachment;
import com.example.service.IAttachmentService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class AttachmentService extends ServiceImpl<AttachmentMapper, Attachment> implements IAttachmentService {

    @Override
    @Cacheable(cacheNames = "attachment:multiple", keyGenerator = "defaultPageKeyGenerator")
    public IPage<Attachment> page(IPage<Attachment> page, Wrapper<Attachment> queryWrapper) {
        return super.page(page, queryWrapper);
    }

    @Override
    @Cacheable(cacheNames = "attachment:single", key = "'attachment:'+#id", unless = "#result==null")
    public Attachment getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = {"attachment:multiple"}, allEntries = true),
                    @CacheEvict(cacheNames = {"attachment:single"}, key = "'attachment:'+#id")
            }
    )
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }
}
