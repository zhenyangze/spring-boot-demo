package com.example.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel("返回结果")
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 成功 */
    public static final int SUCCESS = 200;
    /** 失败 */
    public static final int FAILURE = 500;
    /** 未认证 */
    public static final int UNAUTHORIZED = 401;
    /** 未授权 */
    public static final int FORBIDDEN = 403;
    @ApiModelProperty("状态码")
    private int code;
    @ApiModelProperty("消息")
    private String message;
    @ApiModelProperty("数据")
    private T data;

}
