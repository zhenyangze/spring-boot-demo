package com.example.controller;

import com.example.model.User;
import com.example.param.UserParams;
import com.example.result.Result;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession session;

    @GetMapping("/login")
    public Result login(UserParams params) {
        User user = userService.login(params);
        session.setAttribute("user", user);
        return Result.success(user);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") Integer id) {
        User user = userService.findById(id);
        return Result.success(user);
    }

}
