package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dao.UserDao;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        wrapper.eq("password", user.getPassword());
        User user1 = userDao.selectOne(wrapper);
        return user1;
    }

    @Override
    @Cacheable(cacheNames = "user", key = "'/user/'+#root.method.name+'/'+#id")
    public User findById(Integer id) {
        System.out.println("查询id为"+id+"的用户");
        User user = userDao.selectById(id);
        return user;
    }

}
