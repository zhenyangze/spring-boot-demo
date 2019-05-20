package com.example.service;

import com.example.model.po.Role;

import java.util.List;

public interface IRoleService extends IBaseService<Role> {

    String DEFAULT_ROLE_NAME = "ROLE_LOGIN";

    List<Role> all();

}
