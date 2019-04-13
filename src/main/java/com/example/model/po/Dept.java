package com.example.model.po;

import com.example.group.Insert;
import com.example.group.Update;
import com.example.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@ApiModel(value = "部门")
public class Dept extends BaseModel {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "部门id")
    @Null(groups = {Insert.class}, message = "部门id必须为空")
    @NotNull(groups = {Update.class}, message = "部门id不能为空")
    private Integer id;
    @ApiModelProperty(value = "部门名称")
    @NotNull(groups = {Insert.class}, message = "部门名称不能为空")
    private String  deptName;

}
