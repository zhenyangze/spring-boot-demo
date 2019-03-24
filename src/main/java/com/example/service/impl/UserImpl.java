package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dao.UserDao;
import com.example.model.User;
import com.example.param.UserParams;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(UserParams params) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", params.getUsername());
        wrapper.eq("password", params.getPassword());
        User user = userDao.selectOne(wrapper);
        return user;
    }

    @Override
    public User findById(Integer id) {
        User user = userDao.selectById(id);
        return user;
    }
}
