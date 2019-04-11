package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mapper.UserMapper;
import com.example.model.po.Role;
import com.example.model.po.User;
import com.example.model.po.UserRoleLink;
import com.example.service.IUserRoleLinkService;
import com.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends BaseService<UserMapper, User> implements IUserService {

    @Autowired
    private IUserRoleLinkService userRoleLinkService;

    @Override
    @Transactional
    public void customSave(User user) {
        baseMapper.insert(user);
        Integer userId = user.getId();
        List<Role> roles = user.getRoles();
        List<UserRoleLink> links = new ArrayList<>();
        for (Role role: roles) {
            links.add(new UserRoleLink(userId, role.getId()));
        }
        userRoleLinkService.saveBatch(links);
    }

    @Override
    @Transactional
    public void customUpdate(User user) {
        baseMapper.updateById(user);
        Integer userId = user.getId();
        List<Role> roles = user.getRoles();
        List<UserRoleLink> links = new ArrayList<>();
        for (Role role: roles) {
            links.add(new UserRoleLink(userId, role.getId()));
        }
        userRoleLinkService.merge(links, new QueryWrapper<UserRoleLink>().eq("user_id", userId));
    }

}
