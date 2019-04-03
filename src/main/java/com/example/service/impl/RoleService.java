package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
import java.util.Iterator;
import java.util.List;

@Service
public class RoleService extends BaseService<RoleMapper, Role> implements IRoleService {

    @Autowired
    private IRoleResourceLinkService roleResourceLinkService;

    @Override
    public List<Role> getByResourceId(Integer resourceId) {
        return baseMapper.selectByResourceId(resourceId);
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
        List<RoleResourceLink> oldLinks = roleResourceLinkService.list(new QueryWrapper<>(new RoleResourceLink().setRoleId(roleId)));
        QueryWrapper<RoleResourceLink> queryWrapper = new QueryWrapper<>();
        Iterator<RoleResourceLink> oldLinksIterator = oldLinks.iterator();
        boolean flag = false;
        a: while (oldLinksIterator.hasNext()) {
            RoleResourceLink oldLink = oldLinksIterator.next();
            Iterator<RoleResourceLink> linksIterator = links.iterator();
            while (linksIterator.hasNext()) {
                RoleResourceLink link = linksIterator.next();
                if (oldLink.getResourceId().equals(link.getResourceId())) {
                    UpdateWrapper<RoleResourceLink> updateWrapper = new UpdateWrapper<>();
                    updateWrapper.eq("role_id", link.getRoleId());
                    updateWrapper.eq("resource_id", link.getResourceId());
                    roleResourceLinkService.update(link, updateWrapper);
                    linksIterator.remove();
                    continue a;
                }
            }
            queryWrapper.eq("role_id", oldLink.getRoleId());
            queryWrapper.eq("resource_id", oldLink.getResourceId());
            queryWrapper.or();
            flag = true;
        }
        if (flag) {
            roleResourceLinkService.remove(queryWrapper);
        }
        if (links.size()>0) {
            roleResourceLinkService.saveBatch(links);
        }
    }

}
