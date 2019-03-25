package com.example.model;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class User {

    private Integer id;
    private String username;
    private String password;
    private String nickname;
    @JSONField(format = "yyyy-MM-dd")
    private Date birth;
    @JSONField(format = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value="登录时间", example = "2019-03-25 23:06:01")
    private Timestamp logintime;
    private Integer roleId;
    private Integer deptId;

}
