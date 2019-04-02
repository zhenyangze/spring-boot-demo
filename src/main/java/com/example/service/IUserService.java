package com.example.service;

import com.example.model.po.User;

public interface IUserService extends IBaseService<User> {

    void customSave(User user);

    void customUpdate(User user);

}
