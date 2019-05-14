package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.exception.LogicException;
import com.example.mapper.UserMapper;
import com.example.model.po.*;
import com.example.params.Params;
import com.example.service.IMailService;
import com.example.service.IUserRoleLinkService;
import com.example.service.IUserService;
import com.google.common.collect.Lists;
import io.jsonwebtoken.lang.Collections;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserService extends BaseService<UserMapper, User> implements IUserService {

    @Autowired
    private IUserRoleLinkService userRoleLinkService;
    @Autowired
    private IMailService mailService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

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

    @Override
    @Transactional
    public void retrievePasswordMail(User user) {
        User user_ = baseMapper.customSelectOne(new Params<>(user));
        if (user_==null) {
            throw new LogicException("用户不存在，请检查用户名和密码是否输入正确");
        }
        String code = RandomStringUtils.randomAlphanumeric(6);
        Mail mail = createRetrievePasswordMail(user_, code);
        mailService.customSave(mail);
        mailService.send(mail.getId());
        // 将验证码保存到redis
        redisTemplate.boundValueOps(RETRIEVE_PASSWORD_CODE_PREFIX+user.getUsername()+"-"+user.getEmail()).set(code, 30*60, TimeUnit.SECONDS);
    }

    // 找回密码邮件
    private Mail createRetrievePasswordMail(User user, String code) {
        Mail mail = new Mail();
        mail.setMailSubject("REACT中后台找回密码");
        mail.setMailType("info");
        String content = "";
        content += "<p>您好：</p>";
        content += "<p>您在REACT中后台申请找回密码，验证码：<span style='color:#F00'>"+code+"</span>，有效期30分钟。</p>";
        content += "<p>如果您没有进行过找回密码的操作，请无视此邮件。</p>";
        content += "<p>----------------------------------</p>";
        content += "<p>REACT中后台</p>";
        content += "<p>"+new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())+"</p>";
        mail.setMailContent(new MailContent().setContent(content));
        mail.setToUsers(Lists.newArrayList(user));
        return mail;
    }

    @Override
    @Transactional
    public void retrievePassword(User user, String code) {
        String key = RETRIEVE_PASSWORD_CODE_PREFIX+user.getUsername()+"-"+user.getEmail();
        String code_ = (String) redisTemplate.opsForValue().get(key);
        if (!code.equals(code_)) {
            throw new LogicException("验证码错误！");
        }
        User user_ = baseMapper.customSelectOne(new Params<>(new User().setUsername(user.getUsername())));
        user_.setPassword(user.getPassword());
        baseMapper.updateById(user_);
        // 删除验证码
        redisTemplate.delete(key);
    }

}
