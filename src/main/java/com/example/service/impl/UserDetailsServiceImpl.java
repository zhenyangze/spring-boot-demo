package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.model.vo.UserDetailsImpl;
import com.example.mapper.UserMapper;
import com.example.model.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(true,"username", username);
        User user = userMapper.selectOne(wrapper);
        if (user==null) {
            throw new UsernameNotFoundException("用户名："+ username + "不存在！");
        }
        return new UserDetailsImpl(user);
    }

}
