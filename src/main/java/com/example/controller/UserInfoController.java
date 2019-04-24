package com.example.controller;

import com.example.group.UserInfoInsert;
import com.example.group.UserInfoUpdate;
import com.example.model.po.Dept;
import com.example.model.po.Resource;
import com.example.model.po.Role;
import com.example.model.po.User;
import com.example.model.vo.*;
import com.example.params.Params;
import com.example.service.IRoleService;
import com.example.service.IUserService;
import com.example.util.ModelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.model.vo.ResultVO.SUCCESS;

@RestController
@RequestMapping(value = "/userinfo", produces="application/json; charset=UTF-8")
@Api(tags="用户个人信息")
public class UserInfoController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    @ApiOperation(value = "查询个人信息")
    @Validated
    public ResultVO<UserVO> currentUser() {
        User user = userService.currentUser();
        UserVO userVO = (UserVO) ModelUtil.copy(user,
                new ModelUtil.Mapping(User.class, UserVO.class, "password"),
                new ModelUtil.Mapping(Dept.class, DeptVO.class),
                new ModelUtil.Mapping(Role.class, RoleVO.class),
                new ModelUtil.Mapping(Resource.class, ResourceVO.class));
        return new ResultVO<>(SUCCESS, "", userVO);
    }

    @PostMapping
    @ApiOperation(value = "注册个人信息")
    public ResultVO save(@Validated({UserInfoInsert.class}) UserVO userVO) {
        User user = (User) ModelUtil.copy(userVO, new ModelUtil.Mapping(UserVO.class, User.class));
        String password = user.getPassword();
        if (!StringUtils.isEmpty(password)) {
            user.setPassword(passwordEncoder.encode(password));
        }
        List<Role> defaultRoles = new ArrayList<>();
        defaultRoles.add(roleService.customGetOne(new Params<>(new Role().setRoleName(IRoleService.DEFAULT_ROLE_NAME))));
        userService.customSave(user.setRoles(defaultRoles));
        return new ResultVO<>(SUCCESS, "注册个人信息成功！", null);
    }

    @PutMapping
    @ApiOperation(value = "更新个人信息")
    public ResultVO update(@Validated({UserInfoUpdate.class}) UserVO userVO) {
        User user = (User) ModelUtil.copy(userVO, new ModelUtil.Mapping(UserVO.class, User.class));
        String password = user.getPassword();
        if (!StringUtils.isEmpty(password)) {
            user.setPassword(passwordEncoder.encode(password));
        }
        userService.updateById(user.setId(userService.currentUser().getId()));
        return new ResultVO<>(SUCCESS, "更新个人信息成功！", null);
    }

}
