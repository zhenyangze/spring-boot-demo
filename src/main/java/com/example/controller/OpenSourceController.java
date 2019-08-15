package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.group.OpenSourceInsert;
import com.example.group.OpenSourceUpdate;
import com.example.model.po.OpenSource;
import com.example.model.po.OpenSourceIntro;
import com.example.model.vo.OpenSourceIntroVO;
import com.example.model.vo.OpenSourceVO;
import com.example.model.vo.ResultVO;
import com.example.params.Params;
import com.example.service.IOpenSourceService;
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
@RequestMapping(value = "/open", produces = "application/json; charset=UTF-8")
@Api(tags = "开源组件")
@Validated
public class OpenSourceController {

    @Autowired
    private IOpenSourceService openSourceService;

    @GetMapping("/{current}/{size}")
    @ApiOperation(value = "查询开源组件列表")
    public ResultVO findPage(@PathVariable @NotNull(message = "当前页不能为空") @ApiParam(value = "当前页", defaultValue = "1", required = true) long current,
                             @PathVariable @NotNull(message = "每页显示条数不能为空") @ApiParam(value = "每页显示条数", defaultValue = "10", required = true) long size,
                             OpenSourceVO openSourceVO) {
        Page<OpenSource> page = new Page<>(current, size);
        Params<OpenSource> params = new Params<>(openSourceVO);
        IPage<OpenSource> iPage = openSourceService.customPage(page, params);
        IPage openSources = (IPage) ModelUtil.copy(iPage, new ModelUtil.Mapping(OpenSource.class, OpenSourceVO.class));
        return new ResultVO<>(SUCCESS, "", openSources);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询开源组件")
    public ResultVO<OpenSourceVO> findById(@PathVariable @NotNull(message = "开源组件id不能为空") @ApiParam(value = "开源组件id", required = true) Integer id) {
        OpenSource openSource = openSourceService.customGetById(id);
        OpenSourceVO openSourceVO = (OpenSourceVO) ModelUtil.copy(openSource,
                new ModelUtil.Mapping(OpenSource.class, OpenSourceVO.class),
                new ModelUtil.Mapping(OpenSourceIntro.class, OpenSourceIntroVO.class));
        return new ResultVO<>(SUCCESS, "", openSourceVO);
    }

    @PostMapping
    @ApiOperation(value = "保存开源组件")
    public ResultVO save(@Validated(OpenSourceInsert.class) @RequestBody OpenSourceVO openSourceVO) {
        OpenSource openSource = (OpenSource) ModelUtil.copy(openSourceVO, new ModelUtil.Mapping(OpenSourceVO.class, OpenSource.class));
        openSourceService.customSave(openSource);
        return new ResultVO<>(SUCCESS, "保存开源组件成功！", null);
    }

    @PutMapping
    @ApiOperation(value = "更新开源组件")
    public ResultVO update(@Validated(OpenSourceUpdate.class) @RequestBody OpenSourceVO openSourceVO) {
        OpenSource openSource = (OpenSource) ModelUtil.copy(openSourceVO, new ModelUtil.Mapping(OpenSourceVO.class, OpenSource.class));
        openSourceService.customUpdateById(openSource);
        return new ResultVO<>(SUCCESS, "更新开源组件成功！", null);
    }

    @DeleteMapping
    @ApiOperation(value = "删除开源组件")
    public ResultVO delete(
            @PathVariable
            @NotNull(message = "开源组件id不能为空")
            @NotEmpty(message = "开源组件id不能为空")
            @ApiParam(value = "开源组件id，多个用逗号分隔", required = true) List<Integer> ids) {
        openSourceService.removeByIds(ids);
        return new ResultVO<>(SUCCESS, "删除开源组件成功！", null);
    }

}
