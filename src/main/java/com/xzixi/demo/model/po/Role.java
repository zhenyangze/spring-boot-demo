package com.xzixi.demo.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.xzixi.demo.group.RoleInsert;
import com.xzixi.demo.group.RoleUpdate;
import com.xzixi.demo.group.UserInsert;
import com.xzixi.demo.group.UserUpdate;
import com.xzixi.demo.model.BaseModel;
import com.xzixi.swagger2.plus.annotation.IgnoreSwagger2Parameter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Data
@ApiModel(value = "角色")
public class Role extends BaseModel {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "角色id")
    @NotNull(groups = {RoleUpdate.class, UserInsert.class, UserUpdate.class}, message = "角色id不能为空")
    @Null(groups = {RoleInsert.class}, message = "角色id必须为空")
    private Integer id;
    @ApiModelProperty(value = "角色名称")
    @NotNull(groups = {RoleInsert.class, RoleUpdate.class}, message = "角色名称不能为空")
    private String roleName;
    @ApiModelProperty(value = "角色描述")
    @NotNull(groups = {RoleInsert.class, RoleUpdate.class}, message = "角色描述不能为空")
    private String roleDesc;
    @ApiModelProperty(value = "角色顺序")
    @NotNull(groups = {RoleInsert.class, RoleUpdate.class}, message = "角色顺序不能为空")
    private Integer roleSeq;
    @ApiModelProperty(value = "是否新用户默认角色", allowableValues = "1,0")
    @NotNull(groups = {RoleInsert.class, RoleUpdate.class}, message = "是否新用户默认角色不能为空")
    private Integer isDefault;

    @TableField(exist = false)
    @ApiModelProperty(value = "资源信息")
    @IgnoreSwagger2Parameter
    @NotEmpty(groups = {RoleInsert.class, RoleUpdate.class}, message = "资源信息不能为空")
    @Valid
    private List<Resource> resources;
    @TableField(exist = false)
    @ApiModelProperty(value = "资源pattern集合")
    private String resourcesInfo;

}
