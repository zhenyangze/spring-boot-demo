package com.example.model.po;

import com.example.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("附件")
public class Attachment extends BaseModel {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("附件id")
    private Integer id;
    @ApiModelProperty("名称")
    private String attachmentName;
    @ApiModelProperty("访问地址")
    private String attachmentAddress;
    @ApiModelProperty(value = "本地路径", hidden = true)
    private String attachmentPath;

}
