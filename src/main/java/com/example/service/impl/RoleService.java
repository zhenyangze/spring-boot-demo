package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.RoleMapper;
import com.example.model.po.Role;
import com.example.service.IRoleService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"user:single"}, allEntries = true)
    public boolean updateById(Role role) {
        return super.updateById(role);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"user:single"}, allEntries = true)
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

}
