package com.example.controller;

import com.example.model.User;
import com.example.param.UserParams;
import com.example.result.Result;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public Result login(UserParams params) {
        User user = userService.login(params);
        return Result.success(user);
    }

}
