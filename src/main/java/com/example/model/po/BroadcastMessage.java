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
@ApiModel(value = "广播消息")
public class BroadcastMessage extends BaseModel {

    private static final long serialVersionUID = 1;
    @ApiModelProperty(value = "消息id")
    @Null(groups = {Insert.class}, message = "消息id必须为空")
    private Integer id;
    @ApiModelProperty(value = "发送时间")
    private Timestamp sendTime;
    @ApiModelProperty(value = "消息内容")
    @NotNull(groups = {Insert.class}, message = "消息内容不能为空")
    private String content;
    @ApiModelProperty(value = "发送用户id")
    private Integer sendUserId;

    @TableField(exist = false)
    @ApiModelProperty(value = "发送用户")
    @IgnoreSwaggerParameter
    @Null(groups = {Insert.class}, message = "发送用户必须为空")
    private User sendUser;

}
