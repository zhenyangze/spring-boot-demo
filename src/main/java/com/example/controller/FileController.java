package com.example.controller;

import com.example.exception.ProjectException;
import com.example.model.po.Attachment;
import com.example.model.vo.AttachmentVO;
import com.example.model.vo.ResultVO;
import com.example.service.IAttachmentService;
import com.example.util.ModelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import static com.example.model.vo.ResultVO.SUCCESS;

@RestController
@RequestMapping(value = "/file", produces = "application/json; charset=UTF-8")
@Api(tags = "文件")
@Validated
public class FileController {

    @Autowired
    private IAttachmentService attachmentService;
    @Value("${attachment.save-dir}")
    private String fileDir;
    @Value("${attachment.address-prefix}")
    private String addressPrefix;
    @Value("${attachment.file-separator}")
    private String fileSeparator;

    @PostMapping("/{type}/{rename}")
    @ApiOperation(value = "上传文件")
    public ResultVO<AttachmentVO> upload(@RequestParam("file") @ApiParam(value = "文件", required = true) MultipartFile file,
                                         @PathVariable("type") @NotNull(message = "文件类型不能为空") @ApiParam(value = "文件类型", example = "demo", required = true) String type,
                                         @PathVariable("rename") @NotNull(message = "是否重命名不能为空") @ApiParam(value = "是否重命名", allowableValues = "1,0", defaultValue = "1", required = true) String rename) {
        String name = file.getOriginalFilename();
        if (rename.equals("1")) { // 重命名
            String[] arr = name.split("\\.");
            String exp = arr[arr.length-1];
            name = System.currentTimeMillis()+"."+exp;
        }
        Calendar calendar = Calendar.getInstance();
        // 年
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        // 月
        String month = String.valueOf(calendar.get(Calendar.MONTH)+1);
        // 相对路径
        String relativePath = type+fileSeparator+year+fileSeparator+month+fileSeparator+name;
        // 本地文件路径
        String localPath = fileDir+(fileDir.endsWith(fileSeparator)? "": fileSeparator)+relativePath;
        // 创建文件夹
        File saveFile = new File(localPath);
        File saveDir = new File(saveFile.getParent());
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }
        try {
            // 保存文件
            FileUtils.copyInputStreamToFile(file.getInputStream(), saveFile);
            // file.transferTo(saveFile); // 这个方法不稳定
        } catch (IOException e) {
            throw new ProjectException(e);
        }
        // 访问路径
        String address = addressPrefix+(addressPrefix.endsWith(fileSeparator)? "": fileSeparator)+relativePath;
        Attachment attachment = new Attachment();
        attachment.setAttachmentName(file.getOriginalFilename());
        attachment.setAttachmentPath(localPath);
        attachment.setAttachmentAddress(address);
        attachmentService.save(attachment);
        AttachmentVO attachmentVO = (AttachmentVO) ModelUtil.copy(attachment, new ModelUtil.Mapping(Attachment.class, AttachmentVO.class, "attachmentPath"));
        return new ResultVO<>(SUCCESS, "", attachmentVO);
    }

}
