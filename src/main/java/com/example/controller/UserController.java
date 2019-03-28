package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.group.Insert;
import com.example.group.Update;
import com.example.model.User;
import com.example.result.Result;
import com.example.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

import static com.example.result.Result.SUCCESS;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/user", produces="application/json; charset=UTF-8")
@Api(tags="用户")
public class UserController {

    private IUserService userService;

    @GetMapping("/{current}/{size}")
    @ApiOperation(value = "查询用户列表")
    @Validated
    public Result page(@PathVariable @NotNull(message = "当前页不能为空") @ApiParam(value = "当前页", defaultValue = "1", required = true) long current,
                       @PathVariable @NotNull(message = "每页显示条数不能为空") @ApiParam(value = "每页显示条数", defaultValue = "10", required = true) long size,
                       User user) {
        Page<User> page = new Page<>(current, size);
        Wrapper<User> wrapper = new QueryWrapper<>(user);
        IPage<User> iPage = userService.page(page, wrapper);
        return new Result<>(SUCCESS, "", iPage);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询用户")
    @Validated
    public Result<User> findById(@PathVariable @NotNull(message = "用户id不能为空") @ApiParam(value = "用户id", required = true) Integer id) {
        User user = userService.getById(id);
        return new Result<>(SUCCESS, "", user);
    }

    @PostMapping
    @ApiOperation(value = "保存用户")
    public Result save(@Validated(value = {Insert.class}) User user) {
        userService.save(user);
        return new Result<>(SUCCESS, "保存用户成功！", null);
    }

    @PutMapping
    @ApiOperation(value = "更新用户")
    public Result update(@Validated(value = {Update.class}) User user) {
        userService.updateById(user);
        return new Result<>(SUCCESS, "更新用户成功！", null);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户")
    public Result delete(@PathVariable @NotNull(message = "用户id不能为空") @ApiParam(value = "用户id", required = true) Integer id) {
        userService.removeById(id);
        return new Result<>(SUCCESS, "删除用户成功！", null);
    }

}
