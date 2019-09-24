package com.xzixi.demo.model.vo;

import com.xzixi.demo.model.po.BroadcastMessage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "广播消息")
public class BroadcastMessageVO extends BroadcastMessage {

    private static final long serialVersionUID = 1;
    @ApiModelProperty(value = "阅读状态", allowableValues = "1,0")
    private Integer readStatus;

}
