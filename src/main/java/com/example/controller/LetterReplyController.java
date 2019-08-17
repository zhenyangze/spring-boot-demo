package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.group.LetterReplyInsert;
import com.example.model.po.LetterReply;
import com.example.model.po.User;
import com.example.model.vo.LetterReplyVO;
import com.example.model.vo.ResultVO;
import com.example.model.vo.UserVO;
import com.example.params.Params;
import com.example.service.ILetterReplyService;
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
        Page<LetterReply> page = new Page<>(current, size);
        Params<LetterReply> params = new Params<>(letterReplyVO);
        IPage<LetterReply> iPage = letterReplyService.customPage(page, params);
        IPage replys = (IPage) ModelUtil.copy(iPage,
                new ModelUtil.Mapping(LetterReply.class, LetterReplyVO.class),
                new ModelUtil.Mapping(User.class, UserVO.class));
        return new ResultVO<>(SUCCESS, "", replys);
    }

    @PostMapping
    @ApiOperation(value = "保存回复")
    public ResultVO save(@Validated(LetterReplyInsert.class) LetterReplyVO letterReplyVO) {
        LetterReply letterReply = (LetterReply) ModelUtil.copy(letterReplyVO, new ModelUtil.Mapping(LetterReplyVO.class, LetterReply.class));
        User currentUser = letterReplyService.currentUser();
        long now = System.currentTimeMillis();
        letterReply.setReplyUserId(currentUser.getId());
        letterReply.setReplyTime(now);
        return new ResultVO<>(SUCCESS, "保存回复成功！", null);
    }

    @DeleteMapping("/{ids}")
    @ApiOperation(value = "删除回复")
    public ResultVO delete(
            @PathVariable
            @NotNull(message = "回复id不能为空")
            @NotEmpty(message = "回复id不能为空")
            @ApiParam(value = "回复id，多个用逗号分隔", required = true) List<Integer> ids) {
        letterReplyService.removeByIds(ids);
        return new ResultVO<>(SUCCESS, "删除回复成功！", null);
    }

}
