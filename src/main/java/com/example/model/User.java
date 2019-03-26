package com.example.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@ApiModel("用户")
public class User {

    @ApiModelProperty("用户id")
    private Integer id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("昵称")
    private String nickname;
    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty("生日")
    private Date birth;
    @JSONField(format = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value="登录时间")
    private Timestamp logintime;
    @ApiModelProperty("部门id")
    private Integer deptId;

    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private Dept dept;

}
