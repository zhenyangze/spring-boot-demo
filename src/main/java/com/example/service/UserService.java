package com.example.service;

import com.example.model.User;
import com.example.param.UserParams;

public interface UserService {

    User login(UserParams params);

    User findById(Integer id);

}
