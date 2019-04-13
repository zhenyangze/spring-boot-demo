package com.example.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.example.annotation.IgnoreSwaggerParameter;
import com.example.group.JobTemplateInsert;
import com.example.group.JobTemplateUpdate;
import com.example.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Data
@ApiModel("任务模板")
public class JobTemplate extends BaseModel {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("任务id")
    @Null(groups = {JobTemplateInsert.class}, message = "任务id必须为空")
    @NotNull(groups = {JobTemplateUpdate.class}, message = "任务id不能为空")
    private Integer id;
    @ApiModelProperty("任务名称")
    @NotNull(groups = {JobTemplateInsert.class, JobTemplateUpdate.class}, message = "任务名称不能为空")
    private String jobName;
    @ApiModelProperty("org.quartz.Job的实现类或org.springframework.scheduling.quartz.QuartzJobBean的子类")
    @NotNull(groups = {JobTemplateInsert.class, JobTemplateUpdate.class}, message = "任务类名不能为空")
    private String jobClassName;
    @ApiModelProperty("任务描述")
    @NotNull(groups = {JobTemplateInsert.class, JobTemplateUpdate.class}, message = "任务描述不能为空")
    private String jobDesc;

    @TableField(exist = false)
    @ApiModelProperty("任务参数")
    @IgnoreSwaggerParameter
    private List<JobTemplateParameter> parameters;

}
