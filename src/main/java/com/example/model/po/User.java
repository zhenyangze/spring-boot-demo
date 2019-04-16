package com.example.model.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.example.annotation.IgnoreSwaggerParameter;
import com.example.group.MailInsert;
import com.example.group.MailUpdate;
import com.example.group.UserInsert;
import com.example.group.UserUpdate;
import com.example.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Data
@ApiModel(value = "用户")
public class User extends BaseModel {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "用户id")
    @NotNull(groups = {UserUpdate.class, MailInsert.class, MailUpdate.class}, message = "用户id不能为空")
    @Null(groups = {UserInsert.class}, message = "用户id必须为空")
    private Integer id;
    @ApiModelProperty(value = "用户名")
    @NotNull(groups = {UserInsert.class, UserUpdate.class}, message = "用户名不能为空")
    private String username;
    @ApiModelProperty(value = "密码")
    @NotNull(groups = {UserInsert.class}, message = "密码不能为空")
    private String password;
    @ApiModelProperty(value = "邮箱")
    @NotNull(groups = {UserInsert.class, UserUpdate.class}, message = "邮箱不能为空")
    @Email(groups = {UserInsert.class, UserUpdate.class}, message = "邮箱地址不合法")
    private String email;
    @ApiModelProperty(value = "昵称")
    @NotNull(groups = {UserInsert.class, UserUpdate.class}, message = "昵称不能为空")
    private String nickname;
    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(value = "生日")
    @NotNull(groups = {UserInsert.class, UserUpdate.class}, message = "生日不能为空")
    private Date birth;
    @JSONField(format = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value="登录时间")
    @Null(groups = {UserInsert.class, UserUpdate.class}, message = "登录时间必须为空")
    private Timestamp logintime;
    @ApiModelProperty(value = "部门id")
    @NotNull(groups = {UserInsert.class, UserUpdate.class}, message = "部门id不能为空")
    private Integer deptId;

    @TableField(exist = false)
    @ApiModelProperty(value = "部门信息")
    @IgnoreSwaggerParameter
    @Null(groups = {UserInsert.class, UserUpdate.class}, message = "部门信息必须为空")
    private Dept dept;
    @TableField(exist = false)
    @ApiModelProperty(value = "角色信息")
    @IgnoreSwaggerParameter
    @NotEmpty(groups = {UserInsert.class, UserUpdate.class}, message = "角色信息不能为空")
    @Valid
    private List<Role> roles;

}
