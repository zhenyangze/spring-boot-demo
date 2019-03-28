package com.example.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.example.group.Insert;
import com.example.group.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@ApiModel("用户")
public class User {

    @ApiModelProperty("用户id")
    @NotNull(groups = {Update.class}, message = "用户id不能为空")
    private Integer id;
    @ApiModelProperty("用户名")
    @NotNull(groups = {Insert.class}, message = "用户名不能为空")
    private String username;
    @ApiModelProperty("密码")
    @NotNull(groups = {Insert.class}, message = "密码不能为空")
    private String password;
    @ApiModelProperty("昵称")
    @NotNull(groups = {Insert.class}, message = "昵称不能为空")
    private String nickname;
    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty("生日")
    @NotNull(groups = {Insert.class}, message = "生日不能为空")
    private Date birth;
    @JSONField(format = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value="登录时间")
    @Null(groups = {Insert.class, Update.class}, message = "登录时间必须为空")
    private Timestamp logintime;
    @ApiModelProperty("部门id")
    @NotNull(groups = {Insert.class}, message = "部门id不能为空")
    private Integer deptId;

    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private Dept dept;

}
