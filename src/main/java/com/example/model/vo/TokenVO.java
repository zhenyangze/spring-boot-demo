package com.example.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// 令牌
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenVO implements Serializable {

    private static final long serialVersionUID = 1L;
    // 令牌
    private String token;
    // 登录时间戳
    private Long loginTime;

}
