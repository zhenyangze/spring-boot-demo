package com.example.model.po;

import com.example.annotation.IgnoreSwaggerParameter;
import com.example.group.MailInsert;
import com.example.group.MailUpdate;
import com.example.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Timestamp;
import java.util.List;

@Data
@ApiModel("邮件")
public class Mail extends BaseModel {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "邮件id")
    @Null(groups = {MailInsert.class}, message = "邮件id必须为空")
    @NotNull(groups = {MailUpdate.class}, message = "邮件id不能为空")
    private Integer id;
    @ApiModelProperty(value = "邮件标题")
    @NotNull(groups = {MailInsert.class, MailUpdate.class}, message = "邮件标题不能为空")
    private String mailSubject;
    @ApiModelProperty(value = "邮件类型", allowableValues = "warn,info")
    @NotNull(groups = {MailInsert.class, MailUpdate.class}, message = "邮件类型不能为空")
    private String mailType;
    @ApiModelProperty(value = "邮件状态", allowableValues = "draft,sent")
    @Null(groups = {MailInsert.class, MailUpdate.class}, message = "邮件状态必须为空")
    private String mailStatus;
    @ApiModelProperty(value = "创建时间")
    @Null(groups = {MailInsert.class, MailUpdate.class}, message = "创建时间必须为空")
    private Timestamp createTime;
    @ApiModelProperty(value = "发送时间")
    @Null(groups = {MailInsert.class, MailUpdate.class}, message = "发送时间必须为空")
    private Timestamp sendTime;
    @ApiModelProperty(value = "发送用户id")
    @NotNull(groups = {MailInsert.class, MailUpdate.class}, message = "发送用户id不能为空")
    private Integer sendUserId;
    @ApiModelProperty(value = "接收用户id")
    @NotNull(groups = {MailInsert.class, MailUpdate.class}, message = "接收用户id不能为空")
    private Integer toUserId;

    @ApiModelProperty("邮件内容")
    @NotNull(groups = {MailInsert.class, MailUpdate.class}, message = "邮件内容不能为空")
    @IgnoreSwaggerParameter
    @Valid
    private MailContent mailContent;
    @ApiModelProperty(value = "发送用户")
    @Null(groups = {MailInsert.class, MailUpdate.class}, message = "发送用户必须为空")
    @IgnoreSwaggerParameter
    private User sendUser;
    @ApiModelProperty(value = "接收用户")
    @Null(groups = {MailInsert.class, MailUpdate.class}, message = "接收用户必须为空")
    @IgnoreSwaggerParameter
    private User toUser;
    @ApiModelProperty(value = "邮件附件")
    @IgnoreSwaggerParameter
    @Valid
    private List<Attachment> attachments;

}
