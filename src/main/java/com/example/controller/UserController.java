package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.model.User;
import com.example.result.Result;
import com.example.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import static com.example.result.Result.SUCCESS;

@RestController
@RequestMapping(value = "/user", produces="application/json; charset=UTF-8")
@Api(tags="用户")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/{current}/{size}")
    @ApiOperation(value = "查询用户列表")
    @Cacheable(cacheNames = "user", keyGenerator = "defaultKeyGenerator")
    public Result page(@PathVariable @ApiParam(value = "当前页", defaultValue = "1", required = true) long current,
                       @PathVariable @ApiParam(value = "每页显示条数", defaultValue = "10", required = true) long size,
                       User user) {
        Page<User> page = new Page<>(current, size);
        Wrapper<User> wrapper = new QueryWrapper<>(user);
        IPage<User> iPage = userService.page(page, wrapper);
        return new Result<>(SUCCESS, "", iPage);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询用户")
    @Cacheable(cacheNames = "user", keyGenerator = "defaultKeyGenerator")
    public Result<User> findById(@PathVariable("id") @ApiParam(value = "用户id", required = true) Integer id) {
        User user = userService.getById(id);
        return new Result<>(SUCCESS, "", user);
    }

    @PostMapping
    @ApiOperation(value = "保存用户")
    @CacheEvict(cacheNames = "user", allEntries = true)
    public Result save(User user) {
        userService.save(user);
        return new Result<>(SUCCESS, "保存用户成功！", null);
    }

    @PutMapping
    @ApiOperation(value = "更新用户")
    @CacheEvict(cacheNames = "user", allEntries = true)
    public Result update(User user) {
        userService.updateById(user);
        return new Result<>(SUCCESS, "更新用户成功！", null);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户")
    @CacheEvict(cacheNames = "user", allEntries = true)
    public Result delete(@PathVariable @ApiParam(value = "用户id", required = true) Integer id) {
        userService.removeById(id);
        return new Result<>(SUCCESS, "删除用户成功！", null);
    }

}
