package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.group.Insert;
import com.example.group.Update;
import com.example.model.po.Dept;
import com.example.model.vo.DeptVO;
import com.example.model.vo.ResultVO;
import com.example.service.IDeptService;
import com.example.util.ModelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

import static com.example.model.vo.ResultVO.SUCCESS;

@RestController
@RequestMapping(value = "/dept", produces = "application/json; charset=UTF-8")
@Api(tags = "部门")
@Validated
public class DeptController {

    @Autowired
    private IDeptService deptService;

    @GetMapping("/{current}/{size}")
    @ApiOperation(value = "查询部门列表")
    public ResultVO list(@PathVariable @NotNull(message = "当前页不能为空") @ApiParam(value = "当前页", defaultValue = "1", required = true) long current,
                         @PathVariable @NotNull(message = "每页显示条数不能为空") @ApiParam(value = "当前页", defaultValue = "10", required = true) long size,
                         DeptVO deptVO) {
        Page<Dept> page = new Page<>(current, size);
        Wrapper<Dept> wrapper = new QueryWrapper<>(deptVO);
        IPage<Dept> iPage = deptService.page(page, wrapper);
        IPage depts = (IPage) ModelUtil.copy(iPage, new ModelUtil.Mapping(Dept.class, DeptVO.class));
        return new ResultVO<>(SUCCESS, "", depts);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询部门")
    public ResultVO<DeptVO> findById(@PathVariable @NotNull(message = "部门id不能为空") @ApiParam Integer id) {
        Dept dept = deptService.getById(id);
        DeptVO deptVO = (DeptVO) ModelUtil.copy(dept, new ModelUtil.Mapping(Dept.class, DeptVO.class));
        return new ResultVO<>(SUCCESS, "", deptVO);
    }

    @PostMapping
    @ApiOperation(value = "保存部门")
    public ResultVO save(@Validated({Insert.class}) DeptVO deptVO) {
        Dept dept = (Dept) ModelUtil.copy(deptVO, new ModelUtil.Mapping(DeptVO.class, Dept.class));
        deptService.save(dept);
        return new ResultVO<>(SUCCESS, "保存部门成功！", null);
    }

    @PutMapping
    @ApiOperation(value = "更新部门")
    public ResultVO update(@Validated({Update.class}) DeptVO deptVO) {
        Dept dept = (Dept) ModelUtil.copy(deptVO, new ModelUtil.Mapping(DeptVO.class, Dept.class));
        deptService.updateById(dept);
        return new ResultVO<>(SUCCESS, "更新部门成功！", null);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除部门")
    public ResultVO delete(@PathVariable @NotNull(message = "部门id不能为空") @ApiParam(value = "部门id", required = true) Integer id) {
        deptService.removeById(id);
        return new ResultVO<>(SUCCESS, "删除部门成功！", null);
    }

}
