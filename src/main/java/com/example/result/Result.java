package com.example.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result {

    private Integer status;
    private String  message;
    private Object  data;

    public static Result success(Object data) {
        Result result = new Result(1, "", data);
        return result;
    }

    public static Result success(String message, Object data) {
        Result result = new Result(1, message, data);
        return result;
    }

    public static Result failure(String message) {
        Result result = new Result(0, message, null);
        return result;
    }

    public static Result failure(String message, Object data) {
        Result result = new Result(0, message, data);
        return result;
    }

}
