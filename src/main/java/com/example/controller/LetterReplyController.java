package com.example.controller;

import com.example.group.LetterReplyInsert;
import com.example.model.vo.LetterReplyVO;
import com.example.model.vo.ResultVO;
import com.example.service.ILetterReplyService;
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
@RequestMapping(value = "/letterReply", produces = "application/json; charset=UTF-8")
@Api(tags = "留言回复")
@Validated
public class LetterReplyController {

    @Autowired
    private ILetterReplyService letterReplyService;

    @GetMapping("/{current}/{size}")
    @ApiOperation(value = "查询回复列表")
    public ResultVO findPage(@PathVariable @NotNull(message = "当前页不能为空") @ApiParam(value = "当前页", defaultValue = "1", required = true) long current,
                             @PathVariable @NotNull(message = "每页显示条数不能为空") @ApiParam(value = "每页显示条数", defaultValue = "10", required = true) long size,
                             LetterReplyVO letterReplyVO) {
        return null;
    }

    @PostMapping
    @ApiOperation(value = "保存回复")
    public ResultVO save(@Validated(LetterReplyInsert.class) LetterReplyVO letterReplyVO) {
        return null;
    }

    @DeleteMapping("/{ids}")
    @ApiOperation(value = "删除回复")
    public ResultVO delete(
            @PathVariable
            @NotNull(message = "回复id不能为空")
            @NotEmpty(message = "回复id不能为空")
            @ApiParam(value = "回复id，多个用逗号分隔", required = true) List<Integer> ids) {
        return null;
    }

}
