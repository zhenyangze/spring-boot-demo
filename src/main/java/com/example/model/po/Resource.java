package com.example.model.po;

import com.example.group.Insert;
import com.example.group.RoleInsert;
import com.example.group.RoleUpdate;
import com.example.group.Update;
import com.example.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class Resource extends BaseModel {

    @ApiModelProperty("资源id")
    @Null(groups = {Insert.class}, message = "资源id必须为空")
    @NotNull(groups = {Update.class, RoleInsert.class, RoleUpdate.class}, message = "资源id不能为空")
    private Integer id;
    @ApiModelProperty("资源url")
    @NotNull(groups = {Insert.class, Update.class}, message = "资源url不能为空")
    @Null(groups = {RoleInsert.class, RoleUpdate.class}, message = "资源url必须为空")
    private String resourceUrl;
    @ApiModelProperty("资源描述")
    @NotNull(groups = {Insert.class, Update.class}, message = "资源描述不能为空")
    @Null(groups = {RoleInsert.class, RoleUpdate.class}, message = "资源描述必须为空")
    private String resourceDesc;

}
