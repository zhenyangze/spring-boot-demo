package com.example.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel("返回结果")
public class Result<T> {

    /** 成功 */
    public static final int SUCCESS = 1;
    /** 失败 */
    public static final int FAILURE = 0;
    /** 禁止访问 */
    public static final int FORBIDDEN = -1;
    @ApiModelProperty("状态码")
    private int code;
    @ApiModelProperty("消息")
    private String message;
    @ApiModelProperty("数据")
    private T data;

}
