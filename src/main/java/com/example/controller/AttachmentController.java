package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.model.Attachment;
import com.example.result.Result;
import com.example.service.AttachmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/attachment", produces = "application/json; charset=UTF-8")
@Api(tags = "附件")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @GetMapping("/{current}/{size}")
    @ApiOperation(value = "查询附件列表")
    public Result list(@PathVariable("current") @ApiParam(value = "当前页", defaultValue = "1", example = "1") long current,
                       @PathVariable("size") @ApiParam(value = "当前页", defaultValue = "10", example = "10") long size) {
        IPage<Attachment> page = attachmentService.list(current, size);
        return Result.success(page);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询附件详情")
    public Result findById(@PathVariable("id") @ApiParam(value = "附件id", required = true, example = "1") Integer id) {
        Attachment attachment = attachmentService.findById(id);
        return Result.success(attachment);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除附件")
    public Result delete(@PathVariable("id") @ApiParam(value = "附件id", required = true, example = "1") Integer id) {
        attachmentService.delete(id);
        return Result.success("删除成功！");
    }

}
