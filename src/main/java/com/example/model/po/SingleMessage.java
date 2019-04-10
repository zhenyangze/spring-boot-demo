package com.example.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.example.group.Insert;
import com.example.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@ApiModel("websocket点对点消息")
public class SingleMessage extends BaseModel {

    private static final long serialVersionUID = 1;
    @ApiModelProperty("消息id")
    private Integer id;
    @ApiModelProperty("发送时间")
    private Timestamp sendTime;
    @ApiModelProperty("消息内容")
    @NotNull(groups = {Insert.class}, message = "消息内容不能为空")
    private String content;
    @ApiModelProperty("发送用户id")
    private Integer sendUserId;
    @ApiModelProperty("接收用户id")
    @NotNull(groups = {Insert.class}, message = "接收用户id不能为空")
    private Integer toUserId;

    @TableField(exist = false)
    @ApiModelProperty(value = "发送用户", hidden = true)
    private User sendUser;
    @TableField(exist = false)
    @ApiModelProperty(value = "接收用户", hidden = true)
    private User toUser;

}
