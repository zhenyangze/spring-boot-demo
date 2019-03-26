package com.example.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("部门")
public class Dept {

    @ApiModelProperty("部门id")
    private Integer id;
    @ApiModelProperty("部门名称")
    private String  deptName;

}
