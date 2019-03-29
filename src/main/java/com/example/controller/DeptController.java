package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.group.Insert;
import com.example.group.Update;
import com.example.model.po.Dept;
import com.example.model.vo.DeptVO;
import com.example.result.Result;
import com.example.service.IDeptService;
import com.example.util.CustomizedBeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

import static com.example.result.Result.SUCCESS;

@RestController
@RequestMapping(value = "/dept", produces = "application/json; charset=UTF-8")
@Api(tags = "部门")
@Validated
public class DeptController {

    @Autowired
    private IDeptService deptService;

    @GetMapping("/{current}/{size}")
    @ApiOperation(value = "查询部门列表")
    public Result list(@PathVariable @NotNull(message = "当前页不能为空") @ApiParam(value = "当前页", defaultValue = "1", required = true) long current,
                       @PathVariable @NotNull(message = "每页显示条数不能为空") @ApiParam(value = "当前页", defaultValue = "10", required = true) long size,
                       DeptVO deptVO) {
        Page<Dept> pageParams = new Page<>(current, size);
        Wrapper<Dept> wrapper = new QueryWrapper<>(deptVO);
        IPage<Dept> iPage = deptService.page(pageParams, wrapper);
        IPage page = CustomizedBeanUtils.copyObject(iPage, Page.class);
        return new Result<>(SUCCESS, "", page);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询部门")
    public Result<Dept> findById(@PathVariable @NotNull(message = "部门id不能为空") @ApiParam Integer id) {
        Dept dept = deptService.getById(id);
        DeptVO deptVO = CustomizedBeanUtils.copyObject(dept, DeptVO.class);
        return new Result<>(SUCCESS, "", deptVO);
    }

    @PostMapping
    @ApiOperation(value = "保存部门")
    public Result save(@Validated({Insert.class}) DeptVO deptVO) {
        Dept dept = CustomizedBeanUtils.copyObject(deptVO, Dept.class);
        deptService.save(dept);
        return new Result<>(SUCCESS, "保存部门成功！", null);
    }

    @PutMapping
    @ApiOperation(value = "更新部门")
    public Result update(@Validated({Update.class}) DeptVO deptVO) {
        Dept dept = CustomizedBeanUtils.copyObject(deptVO, Dept.class);
        deptService.updateById(dept);
        return new Result<>(SUCCESS, "更新部门成功！", null);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除部门")
    public Result delete(@PathVariable @NotNull(message = "部门id不能为空") @ApiParam(value = "部门id", required = true) Integer id) {
        deptService.removeById(id);
        return new Result<>(SUCCESS, "删除部门成功！", null);
    }

}
