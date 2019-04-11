package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.group.RoleInsert;
import com.example.group.RoleUpdate;
import com.example.model.po.Resource;
import com.example.model.po.Role;
import com.example.model.vo.ResourceVO;
import com.example.model.vo.ResultVO;
import com.example.model.vo.RoleVO;
import com.example.service.IRoleService;
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
@RequestMapping(value = "/role", produces = "application/json; charset=UTF-8")
@Api(tags = "角色")
@Validated
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping("/{current}/{size}")
    @ApiOperation(value = "查询角色列表")
    public ResultVO list(@PathVariable @NotNull(message = "当前页不能为空") @ApiParam(value = "当前页", defaultValue = "1", required = true) long current,
                         @PathVariable @NotNull(message = "每页显示条数不能为空") @ApiParam(value = "每页显示条数", defaultValue = "10", required = true) long size,
                         RoleVO roleVO) {
        Page<Role> page = new Page<>(current, size);
        Wrapper<Role> wrapper = new QueryWrapper<>(roleVO);
        IPage<Role> iPage = roleService.page(page, wrapper);
        IPage roles = (IPage) ModelUtil.copy(iPage, new ModelUtil.Mapping(Role.class, RoleVO.class));
        return new ResultVO<>(SUCCESS, "", roles);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询角色")
    public ResultVO<RoleVO> findById(@PathVariable @NotNull(message = "角色id不能为空") @ApiParam(value = "角色id", required = true) Integer id) {
        Role role = roleService.customGetById(id);
        RoleVO roleVO = (RoleVO) ModelUtil.copy(role,
                new ModelUtil.Mapping(Role.class, RoleVO.class),
                new ModelUtil.Mapping(Resource.class, ResourceVO.class));
        return new ResultVO<>(SUCCESS, "", roleVO);
    }

    @PostMapping
    @ApiOperation(value = "保存角色")
    public ResultVO save(@Validated({RoleInsert.class}) @RequestBody RoleVO roleVO) {
        Role role = (Role) ModelUtil.copy(roleVO, new ModelUtil.Mapping(RoleVO.class, Role.class));
        roleService.customSave(role);
        return new ResultVO<>(SUCCESS, "保存角色成功！", null);
    }

    @PutMapping
    @ApiOperation(value = "更新角色")
    public ResultVO update(@Validated({RoleUpdate.class}) @RequestBody RoleVO roleVO) {
        Role role = (Role) ModelUtil.copy(roleVO, new ModelUtil.Mapping(RoleVO.class, Role.class));
        roleService.customUpdate(role);
        return new ResultVO<>(SUCCESS, "更新角色成功！", null);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除角色")
    public ResultVO delete(@PathVariable @NotNull(message = "角色id不能为空") @ApiParam(value = "角色id", required = true) Integer id) {
        roleService.removeById(id);
        return new ResultVO<>(SUCCESS, "删除角色成功！", null);
    }

}
