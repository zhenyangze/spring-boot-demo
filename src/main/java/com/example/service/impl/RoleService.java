package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mapper.RoleMapper;
import com.example.model.po.Resource;
import com.example.model.po.Role;
import com.example.model.po.RoleResourceLink;
import com.example.service.IRoleResourceLinkService;
import com.example.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService extends BaseService<RoleMapper, Role> implements IRoleService {

    @Autowired
    private IRoleResourceLinkService roleResourceLinkService;

    @Override
    public Role customGetById(Integer id) {
        return baseMapper.customSelectById(id);
    }

    @Override
    @Transactional
    public void customSave(Role role) {
        baseMapper.insert(role);
        Integer roleId = role.getId();
        List<Resource> resources = role.getResources();
        List<RoleResourceLink> links = new ArrayList<>();
        for (Resource resource: resources) {
            links.add(new RoleResourceLink(roleId, resource.getId()));
        }
        roleResourceLinkService.saveBatch(links);
    }

    @Override
    @Transactional
    public void customUpdate(Role role) {
        baseMapper.updateById(role);
        Integer roleId = role.getId();
        List<Resource> resources = role.getResources();
        List<RoleResourceLink> links = new ArrayList<>();
        for (Resource resource: resources) {
            links.add(new RoleResourceLink(roleId, resource.getId()));
        }
        roleResourceLinkService.merge(links, new QueryWrapper<RoleResourceLink>().eq("role_id", roleId));
    }

}
