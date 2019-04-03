package com.example.config.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mapper.UserMapper;
import com.example.model.po.Role;
import com.example.model.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        List<Role> roles = user.getRoles();
        for (Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),authorities);
    }

}
