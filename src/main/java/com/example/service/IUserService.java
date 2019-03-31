package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.po.User;

public interface IUserService extends IService<User> {

    void customSave(User user);

    void customUpdate(User user);

}
