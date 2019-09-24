package com.xzixi.demo.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.xzixi.demo.annotation.IgnoreSwaggerParameter;
import com.xzixi.demo.group.LetterInsert;
import com.xzixi.demo.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.List;

@Data
@ApiModel(value = "留言")
public class Letter extends BaseModel {

    private static final long serialVersionUID = 1L;
    @Null(groups = {LetterInsert.class}, message = "留言id必须为空")
    private Integer id;
    @ApiModelProperty(value = "留言描述")
    @NotBlank(groups = {LetterInsert.class}, message = "留言描述不能为空")
    private String description;
    @ApiModelProperty(value = "联系方式")
    private String contact;
    @ApiModelProperty(value = "联系方式类型")
    private String contactType;
    @ApiModelProperty(value = "留言内容")
    @NotBlank(groups = {LetterInsert.class}, message = "留言内容不能为空")
    private String letterContent;
    @ApiModelProperty(value = "留言人id")
    @Null(groups = {LetterInsert.class}, message = "留言人id必须为空")
    private Integer letterUserId;
    @ApiModelProperty(value = "留言时间")
    @Null(groups = {LetterInsert.class}, message = "留言时间必须为空")
    private Long letterTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "留言人")
    @IgnoreSwaggerParameter
    @Null(groups = {LetterInsert.class}, message = "留言人必须为空")
    private User letterUser;
    @TableField(exist = false)
    @ApiModelProperty(value = "留言图片")
    @IgnoreSwaggerParameter
    @Valid
    private List<Attachment> attachments;

}
