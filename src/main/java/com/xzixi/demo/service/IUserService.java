package com.xzixi.demo.service;

import com.xzixi.demo.model.po.Mail;
import com.xzixi.demo.model.po.User;

public interface IUserService extends IBaseService<User> {

    String RETRIEVE_PASSWORD_VERIFICATION_PREFIX = "demo:retrievePassword:";

    Mail retrievePasswordMail(User user);

    void retrievePassword(User user, String verification);

}
