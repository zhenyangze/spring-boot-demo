package com.example.controller;

import com.example.model.User;
import com.example.result.Result;
import com.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/user", produces="application/json; charset=UTF-8")
@Api(tags="用户")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession session;

    @GetMapping("/login")
    @ApiOperation(value = "登陆")
    public Result login(User user) {
        User user1 = userService.login(user);
        session.setAttribute("user", user1);
        return Result.success(user1);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询用户")
    public Result findById(@PathVariable("id") Integer id) {
        User user = userService.findById(id);
        return Result.success(user);
    }

    @GetMapping("/save")
    @ApiOperation(value = "保存用户")
    public Result save(User user) {
        userService.save(user);
        return Result.success("保存成功！");
    }

}
