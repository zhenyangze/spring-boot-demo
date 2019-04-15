package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ftp.SftpHelper;
import com.example.model.po.Attachment;
import com.example.model.vo.AttachmentVO;
import com.example.model.vo.ResultVO;
import com.example.service.IAttachmentService;
import com.example.util.ModelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

import static com.example.model.vo.ResultVO.SUCCESS;

@RestController
@RequestMapping(value = "/attachment", produces = "application/json; charset=UTF-8")
@Api(tags = "附件")
@Validated
public class AttachmentController {

    @Value("${attachment.file-separator}")
    private String fileSeparator;
    @Autowired
    private IAttachmentService attachmentService;
    @Autowired
    private SftpHelper sftpHelper;

    @GetMapping("/{current}/{size}")
    @ApiOperation(value = "查询附件列表")
    public ResultVO list(@PathVariable @NotNull(message = "当前页不能为空") @ApiParam(value = "当前页", defaultValue = "1", required = true) long current,
                         @PathVariable @NotNull(message = "每页显示条数不能为空") @ApiParam(value = "每页显示条数", defaultValue = "10", required = true) long size,
                         AttachmentVO attachmentVO) {
        Page<Attachment> page = new Page<>(current, size);
        Wrapper<Attachment> wrapper = new QueryWrapper<>(attachmentVO);
        IPage<Attachment> iPage = attachmentService.page(page, wrapper);
        IPage attachments = (IPage) ModelUtil.copy(iPage, new ModelUtil.Mapping(Attachment.class, AttachmentVO.class));
        return new ResultVO<>(SUCCESS, "", attachments);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询附件")
    public ResultVO<AttachmentVO> findById(@PathVariable @NotNull(message = "附件id不能为空") @ApiParam(value = "附件id", required = true) Integer id) {
        Attachment attachment = attachmentService.getById(id);
        AttachmentVO attachmentVO = (AttachmentVO) ModelUtil.copy(attachment, new ModelUtil.Mapping(Attachment.class, AttachmentVO.class));
        return new ResultVO<>(SUCCESS, "", attachmentVO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除附件")
    public ResultVO removeById(@PathVariable @NotNull(message = "附件id不能为空") @ApiParam(value = "附件id", required = true) Integer id) {
        Attachment attachment = attachmentService.getById(id);
        String path = attachment.getAttachmentPath();
        String dir = path.substring(0, path.lastIndexOf(fileSeparator));
        String name = path.substring(path.lastIndexOf(fileSeparator)+1);
        sftpHelper.delete(dir, name);
        attachmentService.removeById(id);
        return new ResultVO<>(SUCCESS, "删除附件成功！", null);
    }

}
