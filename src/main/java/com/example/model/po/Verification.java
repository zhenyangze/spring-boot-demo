package com.example.model.po;

import com.example.model.BaseModel;
import lombok.Data;

import java.util.Date;

@Data
public class Verification extends BaseModel {

    private static final long serialVersionUID = 1L;
    private String code;               // 验证码值
    private Date   nextCanSendTime;    // 下次可发送时间

}
