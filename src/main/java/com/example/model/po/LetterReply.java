package com.example.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.example.annotation.IgnoreSwaggerParameter;
import com.example.group.LetterReplyInsert;
import com.example.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Data
@ApiModel(value = "留言回复")
public class LetterReply extends BaseModel {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "回复id")
    @Null(groups = {LetterReplyInsert.class}, message = "回复id必须为空")
    private Integer id;
    @ApiModelProperty(value = "回复内容")
    @NotBlank(groups = {LetterReplyInsert.class}, message = "回复内容不能为空")
    private String replyContent;
    @ApiModelProperty(value = "回复人id")
    @Null(groups = {LetterReplyInsert.class}, message = "回复人id必须为空")
    private Integer replyUserId;
    @ApiModelProperty(value = "回复时间")
    @Null(groups = {LetterReplyInsert.class}, message = "回复时间必须为空")
    private Long replyTime;
    @ApiModelProperty(value = "留言id")
    @NotNull(groups = {LetterReplyInsert.class}, message = "留言id不能为空")
    private Integer letterId;

    @TableField(exist = false)
    @ApiModelProperty(value = "回复人")
    @IgnoreSwaggerParameter
    @Null(groups = {LetterReplyInsert.class}, message = "回复人必须为空")
    private User replyUser;
    @TableField(exist = false)
    @ApiModelProperty(value = "留言回复图片")
    @IgnoreSwaggerParameter
    @Valid
    private List<Attachment> attachments;

}
