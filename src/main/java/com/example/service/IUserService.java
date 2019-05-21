package com.example.service;

import com.example.model.po.Mail;
import com.example.model.po.User;

import java.util.List;

public interface IUserService extends IBaseService<User> {

    String RETRIEVE_PASSWORD_VERIFICATION_PREFIX = "demo:retrievePassword:";

    List<User> all();

    Mail retrievePasswordMail(User user);

    void sendRetrievePasswordMail(Integer mailId);

    void retrievePassword(User user, String verification);

}
