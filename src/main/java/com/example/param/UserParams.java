package com.example.param;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class UserParams extends BaseParams {

    private String username;
    private String password;
    private String nickname;
    private Date birth;
    private Timestamp logintime;

}
