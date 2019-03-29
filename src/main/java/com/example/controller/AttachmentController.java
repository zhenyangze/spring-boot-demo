package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.model.po.Attachment;
import com.example.model.vo.AttachmentVO;
import com.example.result.Result;
import com.example.service.IAttachmentService;
import com.example.util.CustomizedBeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.File;

import static com.example.result.Result.SUCCESS;

@RestController
@RequestMapping(value = "/attachment", produces = "application/json; charset=UTF-8")
@Api(tags = "附件")
@Validated
public class AttachmentController {

    @Autowired
    private IAttachmentService attachmentService;

    @GetMapping("/{current}/{size}")
    @ApiOperation(value = "查询附件列表")
    public Result list(@PathVariable @NotNull(message = "当前页不能为空") @ApiParam(value = "当前页", defaultValue = "1", required = true) long current,
                       @PathVariable @NotNull(message = "每页显示条数不能为空") @ApiParam(value = "当前页", defaultValue = "10", required = true) long size,
                       AttachmentVO attachmentVO) {
        Page<Attachment> pageParams = new Page<>(current, size);
        Wrapper<Attachment> wrapper = new QueryWrapper<>(attachmentVO);
        IPage<Attachment> iPage = attachmentService.page(pageParams, wrapper);
        IPage page = CustomizedBeanUtils.copyObject(iPage, Page.class);
        return new Result<>(SUCCESS, "", page);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询附件")
    public Result<AttachmentVO> findById(@PathVariable @NotNull(message = "附件id不能为空") @ApiParam(value = "附件id", required = true) Integer id) {
        Attachment attachment = attachmentService.getById(id);
        AttachmentVO attachmentVO = CustomizedBeanUtils.copyObject(attachment, AttachmentVO.class);
        return new Result<>(SUCCESS, "", attachmentVO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除附件")
    public Result removeById(@PathVariable @NotNull(message = "附件id不能为空") @ApiParam(value = "附件id", required = true) Integer id) {
        Attachment attachment = attachmentService.getById(id);
        new File(attachment.getAttachmentPath()).delete();
        attachmentService.removeById(id);
        return new Result<>(SUCCESS, "删除附件成功！", null);
    }

}
