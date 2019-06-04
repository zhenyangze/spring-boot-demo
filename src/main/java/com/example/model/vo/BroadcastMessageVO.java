package com.example.model.vo;

import com.example.model.po.BroadcastMessage;
import io.swagger.annotations.ApiModelProperty;

public class BroadcastMessageVO extends BroadcastMessage {

    private static final long serialVersionUID = 1;
    @ApiModelProperty(value = "阅读状态", allowableValues = "1,0")
    private Integer readStatus;

}
