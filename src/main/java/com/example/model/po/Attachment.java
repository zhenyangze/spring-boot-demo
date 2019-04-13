package com.example.model.po;

import com.example.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "附件")
public class Attachment extends BaseModel {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "附件id")
    private Integer id;
    @ApiModelProperty(value = "名称")
    private String attachmentName;
    @ApiModelProperty(value = "访问地址")
    private String attachmentAddress;
    @ApiModelProperty(value = "本地路径")
    private String attachmentPath;

}
