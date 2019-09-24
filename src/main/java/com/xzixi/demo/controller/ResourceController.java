package com.xzixi.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzixi.demo.group.Insert;
import com.xzixi.demo.group.Update;
import com.xzixi.demo.model.po.Resource;
import com.xzixi.demo.model.vo.ResourceVO;
import com.xzixi.demo.model.vo.ResultVO;
import com.xzixi.demo.params.Params;
import com.xzixi.demo.service.IResourceService;
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
@RequestMapping(value = "/resource", produces = "application/json; charset=UTF-8")
@Api(tags = "资源")
@Validated
public class ResourceController {

    @Autowired
    private IResourceService resourceService;

    @GetMapping("all")
    @ApiOperation(value = "查询所有资源")
    public ResultVO all() {
        List<Resource> list = resourceService.all();
        List all = (List) ModelUtil.copy(list, new ModelUtil.Mapping(Resource.class, ResourceVO.class));
        return new ResultVO<>(SUCCESS, "", all);
    }

    @GetMapping("categorys")
    @ApiOperation(value = "查询所有资源类别")
    public ResultVO categorys() {
        List<String> categorys = resourceService.categorys();
        return new ResultVO<>(SUCCESS, "", categorys);
    }

    @GetMapping("/{current}/{size}")
    @ApiOperation(value = "查询资源列表")
    public ResultVO findPage(@PathVariable @NotNull(message = "当前页不能为空") @ApiParam(value = "当前页", defaultValue = "1", required = true) long current,
                         @PathVariable @NotNull(message = "每页显示条数不能为空") @ApiParam(value = "每页显示条数", defaultValue = "10", required = true) long size,
                         ResourceVO resourceVO) {
        Page<Resource> page = new Page<>(current, size);
        Params<Resource> params = new Params<>(resourceVO);
        IPage<Resource> iPage = resourceService.customPage(page, params);
        IPage resources = (IPage) ModelUtil.copy(iPage, new ModelUtil.Mapping(Resource.class, ResourceVO.class));
        return new ResultVO<>(SUCCESS, "", resources);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询资源")
    public ResultVO<ResourceVO> findById(@PathVariable @NotNull(message = "资源id不能为空") @ApiParam(value = "资源id", required = true) Integer id) {
        Resource resource = resourceService.getById(id);
        ResourceVO resourceVO = (ResourceVO) ModelUtil.copy(resource, new ModelUtil.Mapping(Resource.class, ResourceVO.class));
        return new ResultVO<>(SUCCESS, "", resourceVO);
    }

    @PostMapping
    @ApiOperation(value = "保存资源")
    public ResultVO save(@Validated({Insert.class}) ResourceVO resourceVO) {
        Resource resource = (Resource) ModelUtil.copy(resourceVO, new ModelUtil.Mapping(ResourceVO.class, Resource.class));
        resourceService.save(resource);
        return new ResultVO<>(SUCCESS, "保存资源成功！", null);
    }

    @PutMapping
    @ApiOperation(value = "更新资源")
    public ResultVO update(@Validated({Update.class}) ResourceVO resourceVO) {
        Resource resource = (Resource) ModelUtil.copy(resourceVO, new ModelUtil.Mapping(ResourceVO.class, Resource.class));
        resourceService.updateById(resource);
        return new ResultVO<>(SUCCESS, "更新资源成功！", null);
    }

    @DeleteMapping("/{ids}")
    @ApiOperation(value = "删除资源")
    public ResultVO delete(
            @PathVariable
            @NotNull(message = "资源id不能为空")
            @NotEmpty(message = "资源id不能为空")
            @ApiParam(value = "资源id，多个用逗号分隔", required = true) List<Integer> ids) {
        resourceService.customRemoveByIds(ids);
        return new ResultVO<>(SUCCESS, "删除资源成功！", null);
    }

}
