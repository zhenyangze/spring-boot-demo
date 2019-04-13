package com.example.service;

import com.example.model.po.User;

public interface IUserService extends IBaseService<User> {

    User customGetById(Integer id);

    void customSave(User user);

    void customUpdateById(User user);

}
