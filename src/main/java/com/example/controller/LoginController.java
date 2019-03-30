package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.exception.LogicException;
import com.example.model.po.User;
import com.example.model.vo.UserVO;
import com.example.model.vo.ResultVO;
import com.example.service.IUserService;
import com.example.util.ModelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;

import static com.example.model.vo.ResultVO.SUCCESS;

@RestController
@RequestMapping(value = "/login", produces="application/json; charset=UTF-8")
@Api(tags = "登录")
@Validated
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
    public ResultVO<User> login(@NotBlank(message = "用户名不能为空") String username,
                                @NotBlank(message = "密码不能为空") String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(true,"username", username);
        wrapper.eq(true,"password", password);
        User user = userService.getOne(wrapper);
        UserVO userVO = (UserVO) ModelUtil.copy(user, new ModelUtil.Mapping(User.class, UserVO.class, "password"));
        if (user!=null) {
            session.setAttribute("user", userVO);
            return new ResultVO<>(SUCCESS, "", userVO);
        }
        throw new LogicException("用户名或密码错误！");
    }

    @DeleteMapping
    @ApiOperation(value = "登出")
    public ResultVO logout() {
        session.removeAttribute("user");
        return new ResultVO<>(SUCCESS, "登出成功！", null);
    }

}
