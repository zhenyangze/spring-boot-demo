package com.example.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.example.group.RoleInsert;
import com.example.group.RoleUpdate;
import com.example.group.UserInsert;
import com.example.group.UserUpdate;
import com.example.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Data
@ApiModel("角色")
public class Role extends BaseModel {

    @ApiModelProperty("角色id")
    @NotNull(groups = {RoleUpdate.class, UserInsert.class, UserUpdate.class}, message = "角色id不能为空")
    @Null(groups = {RoleInsert.class}, message = "角色id必须为空")
    private Integer id;
    @ApiModelProperty("角色名称")
    @NotNull(groups = {RoleInsert.class, RoleUpdate.class}, message = "角色名称不能为空")
    @Null(groups = {UserInsert.class, UserUpdate.class}, message = "角色名称必须为空")
    private String roleName;
    @ApiModelProperty("角色描述")
    @NotNull(groups = {RoleInsert.class, RoleUpdate.class}, message = "角色描述不能为空")
    @Null(groups = {UserInsert.class, UserUpdate.class}, message = "角色描述必须为空")
    private String roleDesc;

    @TableField(exist = false)
    @ApiModelProperty("资源信息")
    @NotEmpty(groups = {RoleInsert.class, RoleInsert.class}, message = "资源信息不能为空")
    @Valid
    private List<Resource> resources;

}
