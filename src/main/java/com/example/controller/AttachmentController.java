package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.model.Attachment;
import com.example.result.Result;
import com.example.service.IAttachmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping(value = "/attachment", produces = "application/json; charset=UTF-8")
@Api(tags = "附件")
public class AttachmentController {

    @Autowired
    private IAttachmentService attachmentService;

    @GetMapping("/{current}/{size}")
    @ApiOperation(value = "查询附件列表")
    public Result list(@PathVariable @ApiParam(value = "当前页", defaultValue = "1", required = true) long current,
                       @PathVariable @ApiParam(value = "当前页", defaultValue = "10", required = true) long size,
                       Attachment attachment) {
        Page<Attachment> page = new Page<>(current, size);
        Wrapper<Attachment> wrapper = new QueryWrapper<>(attachment);
        IPage<Attachment> iPage = attachmentService.page(page, wrapper);
        return Result.success(iPage);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询附件")
    public Result findById(@PathVariable @ApiParam(value = "附件id", required = true) Integer id) {
        Attachment attachment = attachmentService.getById(id);
        return Result.success(attachment);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除附件")
    public Result removeById(@PathVariable @ApiParam(value = "附件id", required = true) Integer id) {
        Attachment attachment = attachmentService.getById(id);
        new File(attachment.getAttachmentPath()).delete();
        attachmentService.removeById(id);
        return Result.success("删除成功！");
    }

}
