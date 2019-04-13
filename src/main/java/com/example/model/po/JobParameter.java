package com.example.model.po;

import com.example.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("定时任务参数")
public class JobParameter extends BaseModel {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("参数id")
    private Integer id;
    @ApiModelProperty("参数名称")
    private String parameterName;
    @ApiModelProperty("参数值")
    private String parameterValue;
    @ApiModelProperty("定时任务id")
    private Integer jobId;

}
