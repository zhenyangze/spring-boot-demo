package com.xzixi.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzixi.demo.group.Insert;
import com.xzixi.demo.group.Update;
import com.xzixi.demo.model.po.Attachment;
import com.xzixi.demo.model.po.Dept;
import com.xzixi.demo.model.po.User;
import com.xzixi.demo.model.vo.AttachmentVO;
import com.xzixi.demo.model.vo.DeptVO;
import com.xzixi.demo.model.vo.ResultVO;
import com.xzixi.demo.model.vo.UserVO;
import com.xzixi.demo.params.Params;
import com.xzixi.demo.service.IDeptService;
import com.xzixi.demo.service.IUserService;
import com.xzixi.demo.util.ModelUtil;
import com.xzixi.demo.util.TreeUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

import static com.xzixi.demo.model.vo.ResultVO.SUCCESS;

@RestController
@RequestMapping(value = "/dept", produces = "application/json; charset=UTF-8")
@Api(tags = "部门")
@Validated
public class DeptController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IDeptService deptService;

    @GetMapping("/structure")
    @ApiOperation(value = "查询组织架构")
    public ResultVO structure() {
        List<Dept> depts = deptService.customList(new Params<>(new Dept()));
        Map<Integer, Dept> deptMap = Maps.newHashMap();
        depts.forEach(dept -> deptMap.put(dept.getId(), dept));
        List<User> users = userService.customList(new Params<>(new User()));
        users.forEach(user -> {
            if (user.getDeptId()==null) {
                return;
            }
            Dept dept = deptMap.get(user.getDeptId());
            List<User> deptUsers = dept.getUsers();
            if (deptUsers==null) {
                deptUsers = Lists.newArrayList();
                dept.setUsers(deptUsers);
            }
            deptUsers.add(user);
        });
        List<Dept> tree = TreeUtil.toTree(depts);
        List data = (List) ModelUtil.copy(tree,
                new ModelUtil.Mapping(Dept.class, DeptVO.class),
                new ModelUtil.Mapping(User.class, UserVO.class, "password"),
                new ModelUtil.Mapping(Attachment.class, AttachmentVO.class, "attachmentPath"));
        return new ResultVO<>(SUCCESS, "", data);
    }

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
        List<Dept> list = deptService.customList(new Params<>(new Dept()));
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
