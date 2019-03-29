package com.example.model.po;

import com.example.group.Insert;
import com.example.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@ApiModel("书籍")
public class Book extends BaseModel {

    @ApiModelProperty("书籍id")
    @Null(groups = {Insert.class}, message = "书籍id必须为空")
    private Integer id;
    @ApiModelProperty("书籍名称")
    @NotNull(groups = {Insert.class}, message = "书籍名称不能为空")
    private String bookName;
    @ApiModelProperty("用户id")
    private Integer userId;

}
