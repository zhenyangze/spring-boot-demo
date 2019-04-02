package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.mapper.ResourceMapper;
import com.example.model.po.Resource;
import com.example.service.IResourceService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class ResourceService extends BaseService<ResourceMapper, Resource> implements IResourceService {

    @Override
    @Cacheable(cacheNames = {"resource:multiple"}, keyGenerator = "defaultPageKeyGenerator")
    public IPage<Resource> page(IPage<Resource> page, Wrapper<Resource> queryWrapper) {
        return super.page(page, queryWrapper);
    }

    @Override
    @Cacheable(cacheNames = {"resource:single"}, key = "'resource:'+#id", unless = "#result==null")
    public Resource getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"resource.multiple"}, allEntries = true)
    public boolean save(Resource resource) {
        return super.save(resource);
    }

    @Override
    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = {"resource:multiple"}, allEntries = true),
                    @CacheEvict(cacheNames = {"resource:single"}, key = "'resource:'+#resource.id")
            }
    )
    public boolean updateById(Resource resource) {
        return super.updateById(resource);
    }

    @Override
    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = {"resource:multiple"}, allEntries = true),
                    @CacheEvict(cacheNames = {"resource:single"}, key = "'resource:'+#id")
            }
    )
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }
}
