package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.model.Dept;
import com.example.result.Result;
import com.example.service.IDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.result.Result.SUCCESS;

@RestController
@RequestMapping(value = "/dept", produces = "application/json; charset=UTF-8")
@Api(tags = "部门")
public class DeptController {

    @Autowired
    private IDeptService deptService;

    @GetMapping("/{current}/{size}")
    @ApiOperation(value = "查询部门列表")
    public Result list(@PathVariable @ApiParam(value = "当前页", defaultValue = "1", required = true) long current,
                       @PathVariable @ApiParam(value = "当前页", defaultValue = "10", required = true) long size,
                       Dept dept) {
        Page<Dept> page = new Page<>(current, size);
        Wrapper<Dept> wrapper = new QueryWrapper<>(dept);
        IPage<Dept> iPage = deptService.page(page, wrapper);
        return new Result<>(SUCCESS, "", iPage);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询部门")
    public Result<Dept> findById(@PathVariable @ApiParam Integer id) {
        Dept dept = deptService.getById(id);
        return new Result<>(SUCCESS, "", dept);
    }

    @PostMapping
    @ApiOperation(value = "保存部门")
    public Result save(Dept dept) {
        deptService.save(dept);
        return new Result<>(SUCCESS, "保存部门成功！", null);
    }

    @PutMapping
    @ApiOperation(value = "更新部门")
    public Result update(Dept dept) {
        deptService.updateById(dept);
        return new Result<>(SUCCESS, "更新部门成功！", null);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除部门")
    public Result delete(@PathVariable @ApiParam(value = "部门id", required = true) Integer id) {
        deptService.removeById(id);
        return new Result<>(SUCCESS, "删除部门成功！", null);
    }

}
