package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.model.User;
import com.example.result.Result;
import com.example.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/login", produces="application/json; charset=UTF-8")
@Api(tags = "登录")
public class LoginController {

    @Autowired
    private IUserService userService;
    @Autowired
    private HttpSession session;

    @PostMapping
    @ApiOperation(value = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name="username", value="用户名", paramType="query", dataType="string", required = true),
            @ApiImplicitParam(name="password", value="密码", paramType="query", dataType="string", required = true)
    })
    public Result login(String username, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(true,"username", username);
        wrapper.eq(true,"password", password);
        User user = userService.getOne(wrapper);
        if (user!=null) {
            session.setAttribute("user", user);
            return Result.success(user);
        }
        return Result.failure("用户名或密码错误！");
    }

    @DeleteMapping
    @ApiOperation(value = "登出")
    public Result logout() {
        session.removeAttribute("user");
        return Result.success("登出成功！");
    }

}
