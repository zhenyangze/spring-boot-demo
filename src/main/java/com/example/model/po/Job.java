package com.example.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.example.annotation.IgnoreSwaggerParameter;
import com.example.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("定时任务")
public class Job extends BaseModel {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("任务id")
    private Integer id;
    private String jobDesc;
    private String schedName;
    private String jobName;
    private String jobGroup;
    private String triggerName;
    private String triggerGroup;
    @ApiModelProperty("任务模板id")
    private Integer jobTemplateId;

    @TableField(exist = false)
    @ApiModelProperty("任务模板")
    @IgnoreSwaggerParameter
    private JobTemplate jobTemplate;
    @TableField(exist = false)
    @ApiModelProperty("任务参数")
    @IgnoreSwaggerParameter
    private List<JobParameter> parameters;

}
