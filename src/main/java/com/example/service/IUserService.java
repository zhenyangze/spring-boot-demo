package com.example.service;

import com.example.model.po.User;

public interface IUserService extends IBaseService<User> {

    String RETRIEVE_PASSWORD_VERIFICATION_PREFIX = "retrievePassword:";

    String retrievePasswordMail(User user);

    void retrievePassword(User user, String verification);

}
