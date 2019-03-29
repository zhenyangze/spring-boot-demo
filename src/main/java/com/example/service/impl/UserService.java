package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.UserMapper;
import com.example.model.po.User;
import com.example.service.IUserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    @Cacheable(cacheNames = {"user:multiple"}, keyGenerator = "defaultPageKeyGenerator")
    public IPage<User> page(IPage<User> page, Wrapper<User> queryWrapper) {
        return super.page(page, queryWrapper);
    }

    @Override
    @Cacheable(cacheNames = {"user:single"}, key = "'user:'+#id", unless = "#result==null")
    public User getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    @CacheEvict(cacheNames = {"user:multiple"}, allEntries = true)
    public boolean save(User user) {
        return super.save(user);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = {"user:multiple"}, allEntries = true),
                    @CacheEvict(cacheNames = {"user:single"}, key = "'user:'+#user.id")
            }
    )
    public boolean updateById(User user) {
        return super.updateById(user);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = {"user:multiple"}, allEntries = true),
                    @CacheEvict(cacheNames = {"user:single"}, key = "'user:'+#id")
            }
    )
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }
}
