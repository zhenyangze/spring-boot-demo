package com.example.model.po;

import com.example.group.Insert;
import com.example.group.Update;
import com.example.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class Dept extends BaseModel {

    @ApiModelProperty("部门id")
    @Null(groups = {Insert.class}, message = "部门id必须为空")
    @NotNull(groups = {Update.class}, message = "部门id不能为空")
    private Integer id;
    @ApiModelProperty("部门名称")
    @NotNull(groups = {Insert.class}, message = "部门名称不能为空")
    private String  deptName;

}