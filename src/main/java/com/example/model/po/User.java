package com.example.model.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.example.group.UserInsert;
import com.example.group.UserUpdate;
import com.example.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Data
public class User extends BaseModel {

    @ApiModelProperty("用户id")
    @NotNull(groups = {UserUpdate.class}, message = "用户id不能为空")
    @Null(groups = {UserInsert.class}, message = "用户id必须为空")
    private Integer id;
    @ApiModelProperty("用户名")
    @NotNull(groups = {UserInsert.class}, message = "用户名不能为空")
    private String username;
    @ApiModelProperty("密码")
    @NotNull(groups = {UserInsert.class}, message = "密码不能为空")
    private String password;
    @ApiModelProperty("昵称")
    @NotNull(groups = {UserInsert.class}, message = "昵称不能为空")
    private String nickname;
    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty("生日")
    @NotNull(groups = {UserInsert.class}, message = "生日不能为空")
    private Date birth;
    @JSONField(format = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value="登录时间")
    @Null(groups = {UserInsert.class, UserUpdate.class}, message = "登录时间必须为空")
    private Timestamp logintime;
    @ApiModelProperty("部门id")
    @NotNull(groups = {UserInsert.class}, message = "部门id不能为空")
    private Integer deptId;

    @TableField(exist = false)
    @ApiModelProperty("部门信息")
    @Null(groups = {UserInsert.class, UserUpdate.class}, message = "部门信息必须为空")
    private Dept dept;
    @TableField(exist = false)
    @ApiModelProperty("书籍信息")
    @NotEmpty(groups = {UserInsert.class, UserUpdate.class}, message = "书籍信息不能为空")
    @Valid
    private List<Book> books;
    @TableField(exist = false)
    @ApiModelProperty("角色信息")
    @NotEmpty(groups = {UserInsert.class, UserUpdate.class}, message = "角色信息不能为空")
    @Valid
    private List<Role> roles;

}
