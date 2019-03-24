package com.example.model;

import com.alibaba.fastjson.annotation.JSONField;
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
    private Timestamp logintime;
    private Integer roleId;
    private Integer deptId;

}
