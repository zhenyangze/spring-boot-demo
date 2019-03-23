package com.example.model;

import lombok.Data;

@Data
public class User {

    private Integer id;
    private String  username;
    private String  password;
    private Integer roleId;
    private Integer deptId;

}
