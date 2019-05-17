package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.group.Insert;
import com.example.group.Update;
import com.example.model.po.Dept;
import com.example.model.vo.DeptVO;
import com.example.model.vo.ResultVO;
import com.example.params.Params;
import com.example.service.IDeptService;
import com.example.util.ModelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.example.model.vo.ResultVO.SUCCESS;

@RestController
@RequestMapping(value = "/dept", produces = "application/json; charset=UTF-8")
@Api(tags = "部门")
@Validated
public class DeptController {

    @Autowired
    private IDeptService deptService;

    @GetMapping("/tree")
    @ApiOperation(value = "部门树形结构")
    public ResultVO tree() {
        List<Dept> list = deptService.tree();
        List tree = (List) ModelUtil.copy(list, new ModelUtil.Mapping(Dept.class, DeptVO.class));
        return new ResultVO<>(SUCCESS, "", tree);
    }

    @GetMapping("/all")
    @ApiOperation(value = "查询所有部门")
    public ResultVO all() {
        List<Dept> list = deptService.all();
        List all = (List) ModelUtil.copy(list, new ModelUtil.Mapping(Dept.class, DeptVO.class));
        return new ResultVO<>(SUCCESS, "", all);
    }

    @GetMapping("/{current}/{size}")
    @ApiOperation(value = "查询部门列表")
    public ResultVO findPage(@PathVariable @NotNull(message = "当前页不能为空") @ApiParam(value = "当前页", defaultValue = "1", required = true) long current,
                         @PathVariable @NotNull(message = "每页显示条数不能为空") @ApiParam(value = "每页显示条数", defaultValue = "10", required = true) long size,
                         DeptVO deptVO) {
        Page<Dept> page = new Page<>(current, size);
        Params<Dept> params = new Params<>(deptVO);
        IPage<Dept> iPage = deptService.customPage(page, params);
        IPage depts = (IPage) ModelUtil.copy(iPage, new ModelUtil.Mapping(Dept.class, DeptVO.class));
        return new ResultVO<>(SUCCESS, "", depts);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询部门")
    public ResultVO<DeptVO> findById(@PathVariable @NotNull(message = "部门id不能为空") @ApiParam(value = "部门id", required = true) Integer id) {
        Dept dept = deptService.getById(id);
        DeptVO deptVO = (DeptVO) ModelUtil.copy(dept, new ModelUtil.Mapping(Dept.class, DeptVO.class));
        return new ResultVO<>(SUCCESS, "", deptVO);
    }

    @PostMapping
    @ApiOperation(value = "保存部门")
    public ResultVO save(@Validated({Insert.class}) DeptVO deptVO) {
        Dept dept = (Dept) ModelUtil.copy(deptVO, new ModelUtil.Mapping(DeptVO.class, Dept.class));
        deptService.customSave(dept);
        return new ResultVO<>(SUCCESS, "保存部门成功！", null);
    }

    @PutMapping
    @ApiOperation(value = "更新部门")
    public ResultVO update(@Validated({Update.class}) DeptVO deptVO) {
        Dept dept = (Dept) ModelUtil.copy(deptVO, new ModelUtil.Mapping(DeptVO.class, Dept.class));
        deptService.customUpdateById(dept);
        return new ResultVO<>(SUCCESS, "更新部门成功！", null);
    }

    @DeleteMapping("/{ids}")
    @ApiOperation(value = "删除部门")
    public ResultVO delete(
            @PathVariable
            @NotNull(message = "部门id不能为空")
            @NotEmpty(message = "部门id不能为空")
            @ApiParam(value = "部门id，多个用逗号分隔", required = true, example = "1,2,3") List<Integer> ids) {
        deptService.customRemoveByIds(ids);
        return new ResultVO<>(SUCCESS, "删除部门成功！", null);
    }

}
