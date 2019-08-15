package com.example.controller;

import com.example.model.po.OpenSourceIntro;
import com.example.model.vo.OpenSourceIntroVO;
import com.example.model.vo.ResultVO;
import com.example.service.IOpenSourceIntroService;
import com.example.util.ModelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import static com.example.model.vo.ResultVO.SUCCESS;

@RestController
@RequestMapping(value = "/openintro", produces = "application/json; charset=UTF-8")
@Api(tags = "开源组件详细说明")
@Validated
public class OpenSourceIntroController {

    @Autowired
    private IOpenSourceIntroService openSourceIntroService;

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询开源组件详细说明")
    public ResultVO<OpenSourceIntro> findById(
            @PathVariable
            @NotNull(message = "开源组件说明id不能为空")
            @ApiParam(value = "开源组件说明id", required = true) Integer id) {
        OpenSourceIntro openSourceIntro = openSourceIntroService.getById(id);
        OpenSourceIntroVO openSourceIntroVO = (OpenSourceIntroVO) ModelUtil.copy(openSourceIntro,
                new ModelUtil.Mapping(OpenSourceIntro.class, OpenSourceIntroVO.class));
        return new ResultVO<>(SUCCESS, "", openSourceIntroVO);
    }

}
