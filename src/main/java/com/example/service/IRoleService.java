package com.example.service;

import com.example.model.po.Role;

import java.util.List;

public interface IRoleService extends IBaseService<Role> {

    List<Role> getByResourceId(Integer resourceId);

    void customSave(Role role);

    void customUpdate(Role role);
}
