package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.DeptMapper;
import com.example.model.Dept;
import com.example.service.IDeptService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class DeptService extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Override
    @Cacheable(cacheNames = {"dept:multiple"}, keyGenerator = "defaultPageKeyGenerator")
    public IPage<Dept> page(IPage<Dept> page, Wrapper<Dept> queryWrapper) {
        return super.page(page, queryWrapper);
    }

    @Override
    @Cacheable(cacheNames = {"dept:single"}, key = "'dept:'+#id", unless = "#result==null")
    public Dept getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    @CacheEvict(cacheNames = {"dept.multiple"}, allEntries = true)
    public boolean save(Dept dept) {
        return super.save(dept);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = {"dept:multiple", "user:single"}, allEntries = true),
                    @CacheEvict(cacheNames = {"dept:single"}, key = "'dept:'+#dept.id")
            }
    )
    public boolean updateById(Dept dept) {
        return super.updateById(dept);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = {"dept:multiple", "user:single"}, allEntries = true),
                    @CacheEvict(cacheNames = {"dept:single"}, key = "'dept:'+#id")
            }
    )
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

}
