package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.mapper.DeptMapper;
import com.example.model.po.Dept;
import com.example.service.IDeptService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class DeptService extends BaseService<DeptMapper, Dept> implements IDeptService {

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
    @Transactional
    @CacheEvict(cacheNames = {"dept.multiple"}, allEntries = true)
    public void customSave(Dept dept) {
        Integer pid = dept.getPid();
        if (pid==null) {
            dept.setLevel(1);
            dept.setFullName(dept.getDeptName());
        } else {
            Dept pDept = baseMapper.selectById(pid);
            dept.setLevel(pDept.getLevel()+1);
            dept.setFullName(pDept.getFullName()+"-"+dept.getDeptName());
        }
        super.save(dept);
    }

    @Override
    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = {"dept:multiple"}, allEntries = true),
                    @CacheEvict(cacheNames = {"dept:single"}, key = "'dept:'+#dept.id")
            }
    )
    public void customUpdateById(Dept dept) {
        super.updateById(dept);
    }

    @Override
    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = {"dept:multiple"}, allEntries = true),
                    @CacheEvict(cacheNames = {"dept:single"}, key = "'dept:'+#id")
            }
    )
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

}
