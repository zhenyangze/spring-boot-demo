package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.UserRoleLinkMapper;
import com.example.model.po.UserRoleLink;
import com.example.service.IUserRoleLinkService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleLinkService extends ServiceImpl<UserRoleLinkMapper, UserRoleLink> implements IUserRoleLinkService {
}
