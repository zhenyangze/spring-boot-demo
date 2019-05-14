package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.exception.LogicException;
import com.example.mapper.UserMapper;
import com.example.model.po.Role;
import com.example.model.po.User;
import com.example.model.po.UserRoleLink;
import com.example.params.Params;
import com.example.service.IUserRoleLinkService;
import com.example.service.IUserService;
import io.jsonwebtoken.lang.Collections;
import org.apache.commons.lang.StringUtils;
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
    public User customGetById(Integer id) {
        return baseMapper.customSelectById(id);
    }

    @Override
    public User customGetOne(Params<User> params) {
        return baseMapper.customSelectOne(params);
    }

    @Override
    @Transactional
    public void customSave(User user) {
        checkUsernameAndEmail(user);
        baseMapper.insert(user);
        Integer userId = user.getId();
        List<Role> roles = user.getRoles();
        List<UserRoleLink> links = new ArrayList<>();
        if (!Collections.isEmpty(roles)) {
            for (Role role: roles) {
                links.add(new UserRoleLink(userId, role.getId()));
            }
            userRoleLinkService.saveBatch(links);
        }
    }

    @Override
    @Transactional
    public void customUpdateById(User user) {
        checkUsernameAndEmail(user);
        baseMapper.updateById(user);
        Integer userId = user.getId();
        List<Role> roles = user.getRoles();
        List<UserRoleLink> links = new ArrayList<>();
        if (!Collections.isEmpty(roles)) {
            for (Role role: roles) {
                links.add(new UserRoleLink(userId, role.getId()));
            }
            userRoleLinkService.merge(links, new QueryWrapper<UserRoleLink>().eq("user_id", userId));
        }
    }

    @Override
    @Transactional
    public boolean updateById(User user) {
        checkUsernameAndEmail(user);
        return super.updateById(user);
    }

    private void checkUsernameAndEmail(User user) {
        String username = user.getUsername();
        if (StringUtils.isNotEmpty(username)) {
            User byUsername = baseMapper.selectOne(new QueryWrapper<>(new User().setUsername(username)));
            if (byUsername!=null && !byUsername.getId().equals(user.getId())) {
                throw new LogicException("用户名已存在！");
            }
        }
        String email = user.getEmail();
        if (StringUtils.isNotEmpty(email)) {
            User byEmail = baseMapper.selectOne(new QueryWrapper<>(new User().setEmail(email)));
            if (byEmail!=null && !byEmail.getId().equals(user.getId())) {
                throw new LogicException("邮箱已存在！");
            }
        }
    }

}
