package com.example.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.example.annotation.IgnoreSwaggerParameter;
import com.example.group.Insert;
import com.example.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Timestamp;

@Data
@ApiModel("广播消息")
public class BroadcastMessage extends BaseModel {

    private static final long serialVersionUID = 1;
    @ApiModelProperty("消息id")
    @Null(groups = {Insert.class}, message = "消息id必须为空")
    private Integer id;
    @ApiModelProperty("发送时间")
    @Null(groups = {Insert.class}, message = "发送时间必须为空")
    private Timestamp sendTime;
    @ApiModelProperty("消息内容")
    @NotNull(groups = {Insert.class}, message = "消息内容不能为空")
    private String content;
    @ApiModelProperty("发送用户id")
    @Null(groups = {Insert.class}, message = "发送用户id必须为空")
    private Integer sendUserId;

    @TableField(exist = false)
    @ApiModelProperty("发送用户")
    @IgnoreSwaggerParameter
    @Null(groups = {Insert.class}, message = "发送用户必须为空")
    private User sendUser;

}
