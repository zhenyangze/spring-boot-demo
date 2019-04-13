package com.example.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.example.annotation.IgnoreSwaggerParameter;
import com.example.group.JobInsert;
import com.example.group.JobUpdate;
import com.example.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Data
@ApiModel(value = "定时任务")
public class Job extends BaseModel {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "任务id")
    @NotNull(groups = {JobUpdate.class}, message = "任务id不能为空")
    @Null(groups = {JobInsert.class}, message = "任务id必须为空")
    private Integer id;
    @ApiModelProperty(value = "任务描述")
    @NotNull(groups = {JobInsert.class, JobUpdate.class}, message = "任务描述不能为空")
    private String jobDesc;
    @ApiModelProperty(value = "任务模板id")
    @NotNull(groups = {JobInsert.class, JobUpdate.class}, message = "任务模板id不能为空")
    private Integer jobTemplateId;
    @ApiModelProperty(value = "调度器名称", hidden = true)
    @IgnoreSwaggerParameter
    @Null(groups = {JobInsert.class, JobUpdate.class}, message = "调度器名称必须为空")
    private String schedName;
    @ApiModelProperty(value = "任务名称", hidden = true)
    @IgnoreSwaggerParameter
    @Null(groups = {JobInsert.class, JobUpdate.class}, message = "任务名称必须为空")
    private String jobName;
    @ApiModelProperty(value = "任务组", hidden = true)
    @IgnoreSwaggerParameter
    @Null(groups = {JobInsert.class, JobUpdate.class}, message = "任务组必须为空")
    private String jobGroup;
    @ApiModelProperty(value = "触发器名称", hidden = true)
    @IgnoreSwaggerParameter
    @Null(groups = {JobInsert.class, JobUpdate.class}, message = "触发器名称必须为空")
    private String triggerName;
    @ApiModelProperty(value = "触发器组", hidden = true)
    @IgnoreSwaggerParameter
    @Null(groups = {JobInsert.class, JobUpdate.class}, message = "触发器组必须为空")
    private String triggerGroup;

    @TableField(exist = false)
    @ApiModelProperty(value = "任务模板")
    @IgnoreSwaggerParameter
    private JobTemplate jobTemplate;
    @TableField(exist = false)
    @ApiModelProperty(value = "任务参数")
    @IgnoreSwaggerParameter
    private List<JobParameter> parameters;

}
