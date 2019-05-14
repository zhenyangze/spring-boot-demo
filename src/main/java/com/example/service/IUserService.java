package com.example.service;

import com.example.model.po.User;

public interface IUserService extends IBaseService<User> {

    String RETRIEVE_PASSWORD_CODE_PREFIX = "retrievePassword:";

    void retrievePasswordMail(User user);

    void retrievePassword(User user, String verification);

}
