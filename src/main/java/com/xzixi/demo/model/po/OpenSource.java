package com.xzixi.demo.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.xzixi.demo.annotation.IgnoreSwaggerParameter;
import com.xzixi.demo.group.OpenSourceInsert;
import com.xzixi.demo.group.OpenSourceUpdate;
import com.xzixi.demo.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Data
@ApiModel(value = "开源组件")
public class OpenSource extends BaseModel {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "开源组件id")
    @NotNull(groups = {OpenSourceUpdate.class}, message = "开源组件id不能为空")
    @Null(groups = {OpenSourceInsert.class}, message = "开源组件id必须为空")
    private Integer id;
    @ApiModelProperty(value = "开源组件名称")
    @NotBlank(groups = {OpenSourceInsert.class, OpenSourceUpdate.class}, message = "开源组件名称不能为空")
    private String sourceName;
    @ApiModelProperty(value = "开源组件描述")
    @NotBlank(groups = {OpenSourceInsert.class, OpenSourceUpdate.class}, message = "开源组件描述不能为空")
    private String sourceDesc;
    @ApiModelProperty(value = "开源组件顺序")
    @NotNull(groups = {OpenSourceInsert.class, OpenSourceUpdate.class}, message = "开源组件顺序不能为空")
    private Integer sourceSeq;
    @ApiModelProperty(value = "gitee地址")
    @NotBlank(groups = {OpenSourceInsert.class, OpenSourceUpdate.class}, message = "gitee地址不能为空")
    private String giteeUrl;
    @ApiModelProperty(value = "github地址")
    @NotBlank(groups = {OpenSourceInsert.class, OpenSourceUpdate.class}, message = "github地址不能为空")
    private String githubUrl;
    @ApiModelProperty(value = "录入人id")
    @Null(groups = {OpenSourceInsert.class, OpenSourceUpdate.class}, message = "录入人id必须为空")
    private Integer saveUserId;
    @ApiModelProperty(value = "录入时间")
    @Null(groups = {OpenSourceInsert.class, OpenSourceUpdate.class}, message = "录入时间必须为空")
    private Long saveTime;
    @ApiModelProperty(value = "最后修改人id")
    @Null(groups = {OpenSourceInsert.class, OpenSourceUpdate.class}, message = "最后修改人id必须为空")
    private Integer updateUserId;
    @ApiModelProperty(value = "最后修改时间")
    @Null(groups = {OpenSourceInsert.class, OpenSourceUpdate.class}, message = "最后修改时间必须为空")
    private Long updateTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "开源组件详细说明")
    @IgnoreSwaggerParameter
    @Valid
    @NotNull(groups = {OpenSourceInsert.class, OpenSourceUpdate.class}, message = "开源组件详细说明不能为空")
    private OpenSourceIntro intro;
    @TableField(exist = false)
    @ApiModelProperty(value = "录入人")
    @IgnoreSwaggerParameter
    @Null(groups = {OpenSourceInsert.class, OpenSourceUpdate.class}, message = "录入人必须为空")
    private User saveUser;
    @TableField(exist = false)
    @ApiModelProperty(value = "最后修改人")
    @IgnoreSwaggerParameter
    @Null(groups = {OpenSourceInsert.class, OpenSourceUpdate.class}, message = "最后修改人必须为空")
    private User updateUser;
    @TableField(exist = false)
    @ApiModelProperty(value = "开源组件图片")
    @IgnoreSwaggerParameter
    @Valid
    private List<Attachment> attachments;

}
