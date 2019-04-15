package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.model.po.Mail;
import com.example.model.vo.MailVO;
import com.example.model.vo.ResultVO;
import com.example.params.Params;
import com.example.service.IMailService;
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
@RequestMapping(value = "/mail", produces = "application/json; charset=UTF-8")
@Api(tags = "邮件")
@Validated
public class MailController {

    @Autowired
    private IMailService mailService;

    @GetMapping("/{current}/{size}")
    @ApiOperation("查询邮件列表")
    public ResultVO findPage(@PathVariable @NotNull(message = "当前页不能为空") @ApiParam(value = "当前页", defaultValue = "1", required = true) long current,
                         @PathVariable @NotNull(message = "每页显示条数不能为空") @ApiParam(value = "每页显示条数", defaultValue = "10", required = true) long size,
                         MailVO mailVO) {
        Page<Mail> page = new Page<>(current, size);
        Params<Mail> params = new Params<>(mailVO);
        IPage<Mail> iPage = mailService.customPage(page, params);
        IPage mails = (IPage) ModelUtil.copy(iPage, new ModelUtil.Mapping(Mail.class, MailVO.class));
        return new ResultVO<>(SUCCESS, "", mails);
    }

}
