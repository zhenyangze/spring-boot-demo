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
public class Result {

    @ApiModelProperty("状态码")
    private Integer code;
    @ApiModelProperty("消息")
    private String  message;
    @ApiModelProperty("数据")
    private Object  data;

    public static Result success(String message) {
        return new Result(1, message, null);
    }

    public static Result success(Object data) {
        return new Result(1, "", data);
    }

    public static Result success(String message, Object data) {
        return new Result(1, message, data);
    }

    public static Result failure(String message) {
        return new Result(0, message, null);
    }

    public static Result failure(Object data) {
        return new Result(0, "", data);
    }

    public static Result failure(String message, Object data) {
        return new Result(0, message, data);
    }

}
