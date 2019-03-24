package com.example.service;

import com.example.model.User;

public interface UserService {

    User login(User user);

    User findById(Integer id);

}
