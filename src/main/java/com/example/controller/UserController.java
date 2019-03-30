package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.group.Insert;
import com.example.group.Update;
import com.example.model.po.Book;
import com.example.model.po.User;
import com.example.model.vo.UserVO;
import com.example.model.vo.ResultVO;
import com.example.service.IBookService;
import com.example.service.IUserService;
import com.example.util.ModelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

import static com.example.model.vo.ResultVO.SUCCESS;

@RestController
@RequestMapping(value = "/user", produces="application/json; charset=UTF-8")
@Api(tags="用户")
@Validated
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IBookService bookService;

    @GetMapping("/{current}/{size}")
    @ApiOperation(value = "查询用户列表")
    public ResultVO page(@PathVariable @NotNull(message = "当前页不能为空") @ApiParam(value = "当前页", defaultValue = "1", required = true) long current,
                         @PathVariable @NotNull(message = "每页显示条数不能为空") @ApiParam(value = "每页显示条数", defaultValue = "10", required = true) long size,
                         UserVO userVO) {
        Page<User> pageParams = new Page<>(current, size);
        Wrapper<User> wrapper = new QueryWrapper<>(userVO);
        IPage<User> iPage = userService.page(pageParams, wrapper);
        long start = System.currentTimeMillis();
        IPage page = (IPage) ModelUtil.copy(iPage, new ModelUtil.Mapping(User.class, UserVO.class, "password"));
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        return new ResultVO<>(SUCCESS, "", page);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询用户")
    @Validated
    public ResultVO<UserVO> findById(@PathVariable @NotNull(message = "用户id不能为空") @ApiParam(value = "用户id", required = true) Integer id) {
        User user = userService.getById(id);
        UserVO userVO = (UserVO) ModelUtil.copy(user, new ModelUtil.Mapping(User.class, UserVO.class, "password"));
        return new ResultVO<>(SUCCESS, "", userVO);
    }

    @PostMapping
    @ApiOperation(value = "保存用户")
    public ResultVO save(@Validated({Insert.class}) @RequestBody UserVO userVO) {
        User user = (User) ModelUtil.copy(userVO, new ModelUtil.Mapping(UserVO.class, User.class));
        userService.save(user);
        Integer userId = user.getId();
        List<Book> books = user.getBooks();
        for (Book book: books) {
            book.setUserId(userId);
        }
        bookService.saveBatch(books);
        return new ResultVO<>(SUCCESS, "保存用户成功！", null);
    }

    @PutMapping
    @ApiOperation(value = "更新用户")
    public ResultVO update(@Validated({Update.class}) @RequestBody UserVO userVO) {
        User user = (User) ModelUtil.copy(userVO, new ModelUtil.Mapping(UserVO.class, User.class));
        userService.updateById(user);
        Integer userId = user.getId();
        List<Book> books = user.getBooks();
        for (Book book: books) {
            book.setUserId(userId);
        }
        bookService.saveOrUpdateBatch(books);
        return new ResultVO<>(SUCCESS, "更新用户成功！", null);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户")
    public ResultVO delete(@PathVariable @NotNull(message = "用户id不能为空") @ApiParam(value = "用户id", required = true) Integer id) {
        userService.removeById(id);
        return new ResultVO<>(SUCCESS, "删除用户成功！", null);
    }

}
