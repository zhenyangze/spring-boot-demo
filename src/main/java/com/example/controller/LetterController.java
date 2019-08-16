package com.example.controller;

import com.example.group.LetterInsert;
import com.example.model.vo.LetterVO;
import com.example.model.vo.ResultVO;
import com.example.service.ILetterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/letter", produces = "application/json; charset=UTF-8")
@Api(tags = "留言")
@Validated
public class LetterController {

    @Autowired
    private ILetterService letterService;

    @GetMapping("/{current}/{size}")
    @ApiOperation(value = "查询留言列表")
    public ResultVO findPage(@PathVariable @NotNull(message = "当前页不能为空") @ApiParam(value = "当前页", defaultValue = "1", required = true) long current,
                             @PathVariable @NotNull(message = "每页显示条数不能为空") @ApiParam(value = "每页显示条数", defaultValue = "10", required = true) long size,
                             LetterVO letterVO) {
        return null;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询留言")
    public ResultVO<LetterVO> findById(@PathVariable @NotNull(message = "留言id不能为空") @ApiParam(value = "留言id", required = true) Integer id) {
        return null;
    }

    @PostMapping
    @ApiOperation(value = "保存留言")
    public ResultVO save(@Validated(LetterInsert.class) LetterVO letterVO) {
        return null;
    }

    @DeleteMapping("/{ids}")
    @ApiOperation(value = "删除留言")
    public ResultVO delete(
            @PathVariable
            @NotNull(message = "留言id不能为空")
            @NotEmpty(message = "留言id不能为空")
            @ApiParam(value = "留言id，多个用逗号分隔", required = true) List<Integer> ids) {
        return null;
    }

}
