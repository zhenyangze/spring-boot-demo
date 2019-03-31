package com.example.model.po;

import com.example.group.Insert;
import com.example.group.Update;
import com.example.group.UserInsert;
import com.example.group.UserUpdate;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class Role {

    @ApiModelProperty("角色id")
    @NotNull(groups = {Update.class, UserInsert.class, UserUpdate.class}, message = "角色id不能为空")
    @Null(groups = {Insert.class}, message = "角色id必须为空")
    private Integer id;
    @ApiModelProperty("角色名称")
    @NotNull(groups = {Insert.class, Update.class}, message = "角色名称不能为空")
    @Null(groups = {UserInsert.class, UserUpdate.class}, message = "角色名称必须为空")
    private String roleName;

}
