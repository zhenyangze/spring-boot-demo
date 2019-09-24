package com.xzixi.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzixi.demo.group.RoleInsert;
import com.xzixi.demo.group.RoleUpdate;
import com.xzixi.demo.model.po.Resource;
import com.xzixi.demo.model.po.Role;
import com.xzixi.demo.model.vo.ResourceVO;
import com.xzixi.demo.model.vo.ResultVO;
import com.xzixi.demo.model.vo.RoleVO;
import com.xzixi.demo.params.Params;
import com.xzixi.demo.service.IRoleService;
import com.xzixi.demo.util.ModelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.xzixi.demo.model.vo.ResultVO.SUCCESS;

@RestController
@RequestMapping(value = "/role", produces = "application/json; charset=UTF-8")
@Api(tags = "角色")
@Validated
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping("all")
    @ApiOperation(value = "查询所有角色")
    public ResultVO all() {
        List<Role> list = roleService.customList(new Params<>(new Role()));
        List all = (List) ModelUtil.copy(list, new ModelUtil.Mapping(Role.class, RoleVO.class));
        return new ResultVO<>(SUCCESS, "", all);
    }

    @GetMapping("/{current}/{size}")
    @ApiOperation(value = "查询角色列表")
    public ResultVO findPage(@PathVariable @NotNull(message = "当前页不能为空") @ApiParam(value = "当前页", defaultValue = "1", required = true) long current,
                         @PathVariable @NotNull(message = "每页显示条数不能为空") @ApiParam(value = "每页显示条数", defaultValue = "10", required = true) long size,
                         RoleVO roleVO) {
        Page<Role> page = new Page<>(current, size);
        Params<Role> params = new Params<>(roleVO);
        IPage<Role> iPage = roleService.customPage(page, params);
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
        roleService.customUpdateById(role);
        return new ResultVO<>(SUCCESS, "更新角色成功！", null);
    }

    @DeleteMapping("/{ids}")
    @ApiOperation(value = "删除角色")
    public ResultVO delete(
            @PathVariable
            @NotNull(message = "角色id不能为空")
            @NotEmpty(message = "角色id不能为空")
            @ApiParam(value = "角色id，多个用逗号分隔", required = true) List<Integer> ids) {
        roleService.removeByIds(ids);
        return new ResultVO<>(SUCCESS, "删除角色成功！", null);
    }

}
