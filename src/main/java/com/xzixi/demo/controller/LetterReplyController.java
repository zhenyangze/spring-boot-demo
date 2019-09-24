package com.xzixi.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzixi.demo.group.LetterReplyInsert;
import com.xzixi.demo.model.po.LetterReply;
import com.xzixi.demo.model.po.Mail;
import com.xzixi.demo.model.po.User;
import com.xzixi.demo.model.vo.LetterReplyVO;
import com.xzixi.demo.model.vo.ResultVO;
import com.xzixi.demo.model.vo.UserVO;
import com.xzixi.demo.params.Params;
import com.xzixi.demo.service.ILetterReplyService;
import com.xzixi.demo.service.IMailService;
import com.xzixi.demo.util.ModelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.xzixi.demo.model.vo.ResultVO.SUCCESS;

@RestController
@RequestMapping(value = "/letterReply", produces = "application/json; charset=UTF-8")
@Api(tags = "留言回复")
@Validated
public class LetterReplyController {

    @Value("${letter-notify.max-retry}")
    private Integer maxRetry;
    @Autowired
    private ILetterReplyService letterReplyService;
    @Autowired
    private IMailService mailService;

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

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询留言回复")
    public ResultVO<LetterReplyVO> findById(@PathVariable @NotNull(message = "留言回复id不能为空") @ApiParam(value = "留言回复id", required = true) Integer id) {
        LetterReply letterReply = letterReplyService.customGetById(id);
        LetterReplyVO letterReplyVO = (LetterReplyVO) ModelUtil.copy(letterReply, new ModelUtil.Mapping(LetterReply.class, LetterReplyVO.class));
        return new ResultVO<>(SUCCESS, "", letterReplyVO);
    }

    @PostMapping
    @ApiOperation(value = "保存回复")
    public ResultVO save(@Validated(LetterReplyInsert.class) @RequestBody LetterReplyVO letterReplyVO) {
        LetterReply letterReply = (LetterReply) ModelUtil.copy(letterReplyVO, new ModelUtil.Mapping(LetterReplyVO.class, LetterReply.class));
        letterReplyService.customSave(letterReply);
        Mail mail = letterReplyService.notifyMail(letterReply);
        mail = mailService.send(mail.getId());
        mailService.send(mail, maxRetry);
        return new ResultVO<>(SUCCESS, "保存回复成功！", null);
    }

    @DeleteMapping("/{ids}")
    @ApiOperation(value = "删除回复")
    public ResultVO delete(
            @PathVariable
            @NotNull(message = "回复id不能为空")
            @NotEmpty(message = "回复id不能为空")
            @ApiParam(value = "回复id，多个用逗号分隔", required = true) List<Integer> ids) {
        letterReplyService.customRemoveByIds(ids);
        return new ResultVO<>(SUCCESS, "删除回复成功！", null);
    }

}
