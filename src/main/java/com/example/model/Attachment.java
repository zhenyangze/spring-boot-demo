package com.example.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

// 附件
@Data
@ApiModel("附件")
public class Attachment {

    @ApiModelProperty("附件id")
    private Integer id;
    @ApiModelProperty("名称")
    private String attachmentName;
    @ApiModelProperty("访问地址")
    private String attachmentAddress;
    @ApiModelProperty("本地路径")
    private String attachmentPath;

}
