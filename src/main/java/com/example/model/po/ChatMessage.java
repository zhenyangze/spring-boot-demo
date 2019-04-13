package com.example.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.example.annotation.IgnoreSwaggerParameter;
import com.example.group.Insert;
import com.example.group.Update;
import com.example.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Timestamp;

@Data
@ApiModel(value = "点对点消息")
public class ChatMessage extends BaseModel {

    private static final long serialVersionUID = 1;
    @ApiModelProperty(value = "消息id")
    @Null(groups = {Insert.class}, message = "消息id必须为空")
    @NotNull(groups = {Update.class}, message = "消息id不能为空")
    private Integer id;
    @ApiModelProperty(value = "发送时间")
    @Null(groups = {Insert.class, Update.class}, message = "发送时间必须为空")
    private Timestamp sendTime;
    @ApiModelProperty(value = "消息内容")
    @NotNull(groups = {Insert.class}, message = "消息内容不能为空")
    @Null(groups = {Update.class}, message = "消息内容必须为空")
    private String content;
    @ApiModelProperty(value = "发送用户id")
    @Null(groups = {Insert.class, Update.class}, message = "发送用户id必须为空")
    private Integer sendUserId;
    @ApiModelProperty(value = "接收用户id")
    @NotNull(groups = {Insert.class}, message = "接收用户id不能为空")
    @Null(groups = {Update.class}, message = "接收用户id必须为空")
    private Integer toUserId;
    @ApiModelProperty(value = "阅读状态")
    @Null(groups = {Insert.class}, message = "阅读状态必须为空")
    private Integer readStatus;

    @TableField(exist = false)
    @ApiModelProperty(value = "发送用户")
    @IgnoreSwaggerParameter
    @Null(groups = {Insert.class, Update.class}, message = "发送用户必须为空")
    private User sendUser;
    @TableField(exist = false)
    @ApiModelProperty(value = "接收用户")
    @IgnoreSwaggerParameter
    @Null(groups = {Insert.class, Update.class}, message = "接收用户必须为空")
    private User toUser;

}
