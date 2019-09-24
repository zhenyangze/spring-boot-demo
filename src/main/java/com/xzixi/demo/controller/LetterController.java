package com.xzixi.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzixi.demo.group.LetterInsert;
import com.xzixi.demo.model.po.Letter;
import com.xzixi.demo.model.po.Mail;
import com.xzixi.demo.model.po.User;
import com.xzixi.demo.model.vo.LetterVO;
import com.xzixi.demo.model.vo.ResultVO;
import com.xzixi.demo.model.vo.UserVO;
import com.xzixi.demo.params.Params;
import com.xzixi.demo.service.ILetterService;
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
@RequestMapping(value = "/letter", produces = "application/json; charset=UTF-8")
@Api(tags = "留言")
@Validated
public class LetterController {

    @Value("${letter-notify.max-retry}")
    private Integer maxRetry;
    @Autowired
    private ILetterService letterService;
    @Autowired
    private IMailService mailService;

    @GetMapping("/{current}/{size}")
    @ApiOperation(value = "查询留言列表")
    public ResultVO findPage(@PathVariable @NotNull(message = "当前页不能为空") @ApiParam(value = "当前页", defaultValue = "1", required = true) long current,
                             @PathVariable @NotNull(message = "每页显示条数不能为空") @ApiParam(value = "每页显示条数", defaultValue = "10", required = true) long size,
                             LetterVO letterVO) {
        Page<Letter> page = new Page<>(current, size);
        Params<Letter> params = new Params<>(letterVO);
        IPage<Letter> iPage = letterService.customPage(page, params);
        IPage letters = (IPage) ModelUtil.copy(iPage,
                new ModelUtil.Mapping(Letter.class, LetterVO.class),
                new ModelUtil.Mapping(User.class, UserVO.class, "password"));
        return new ResultVO<>(SUCCESS, "", letters);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询留言")
    public ResultVO<LetterVO> findById(@PathVariable @NotNull(message = "留言id不能为空") @ApiParam(value = "留言id", required = true) Integer id) {
        Letter letter = letterService.customGetById(id);
        LetterVO letterVO = (LetterVO) ModelUtil.copy(letter, new ModelUtil.Mapping(Letter.class, LetterVO.class));
        return new ResultVO<>(SUCCESS, "", letterVO);
    }

    @PostMapping
    @ApiOperation(value = "保存留言")
    public ResultVO save(@Validated(LetterInsert.class) @RequestBody LetterVO letterVO) {
        Letter letter = (Letter) ModelUtil.copy(letterVO, new ModelUtil.Mapping(LetterVO.class, Letter.class));
        letterService.customSave(letter);
        Mail mail = letterService.notifyMail(letter);
        mail = mailService.send(mail.getId());
        mailService.send(mail, maxRetry);
        return new ResultVO<>(SUCCESS, "保存留言成功！", null);
    }

    @DeleteMapping("/{ids}")
    @ApiOperation(value = "删除留言")
    public ResultVO delete(
            @PathVariable
            @NotNull(message = "留言id不能为空")
            @NotEmpty(message = "留言id不能为空")
            @ApiParam(value = "留言id，多个用逗号分隔", required = true) List<Integer> ids) {
        letterService.customRemoveByIds(ids);
        return new ResultVO<>(SUCCESS, "删除留言成功！", null);
    }

}
