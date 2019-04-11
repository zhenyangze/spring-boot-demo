package com.example.service;

import com.example.model.po.Role;

public interface IRoleService extends IBaseService<Role> {

    Role customGetById(Integer id);

    void customSave(Role role);

    void customUpdate(Role role);

}
