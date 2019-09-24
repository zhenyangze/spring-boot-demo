package com.xzixi.demo.model.po;

import com.xzixi.demo.group.OpenSourceInsert;
import com.xzixi.demo.group.OpenSourceUpdate;
import com.xzixi.demo.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@ApiModel(value = "开源组件详细说明")
public class OpenSourceIntro extends BaseModel {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "说明id")
    @Null(groups = {OpenSourceInsert.class}, message = "说明id必须为空")
    @NotNull(groups = {OpenSourceUpdate.class}, message = "说明id不能为空")
    private Integer id;
    @ApiModelProperty(value = "说明内容")
    @NotBlank(groups = {OpenSourceInsert.class, OpenSourceUpdate.class}, message = "说明内容不能为空")
    private String readme;
    @ApiModelProperty(value = "开源组件id")
    @Null(groups = {OpenSourceInsert.class}, message = "开源组件id必须为空")
    private Integer openSourceId;

}
