package com.example.controller;

import com.example.model.Attachment;
import com.example.result.Result;
import com.example.service.AttachmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

@RestController
@RequestMapping(value = "/file", produces = "application/json; charset=UTF-8")
@Api(tags = "文件")
public class FileController {

    @Autowired
    private AttachmentService attachmentService;
    @Value("${attachment.save-dir}")
    private String fileDir;
    @Value("${attachment.address-prefix}")
    private String addressPrefix;
    @Value("${attachment.file-separator}")
    private String fileSeparator;

    @PostMapping("/{type}/{rename}")
    @ApiOperation(value = "上传文件")
    public Result upload(@RequestParam("file") @ApiParam("文件") MultipartFile file,
                         @PathVariable("type") @ApiParam("文件类型") String type,
                         @PathVariable("rename") @ApiParam(value = "是否重命名", allowableValues = "1,0", defaultValue = "1", required = true) String rename) {
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
            file.transferTo(saveFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 访问路径
        String address = addressPrefix+(addressPrefix.endsWith(fileSeparator)? "": fileSeparator)+relativePath;
        Attachment attachment = new Attachment();
        attachment.setAttachmentName(file.getOriginalFilename());
        attachment.setAttachmentPath(localPath);
        attachment.setAttachmentAddress(address);
        attachment = attachmentService.save(attachment);
        return Result.success(attachment);
    }

}
