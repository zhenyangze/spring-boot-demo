package com.example.config.security.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("token")
public class Token implements Serializable {

    @ApiModelProperty("token")
    private String token;
    @ApiModelProperty("登陆时间戳")
    private Long loginTime;

}
