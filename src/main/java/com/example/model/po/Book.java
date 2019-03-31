package com.example.model.po;

import com.example.group.Insert;
import com.example.group.Update;
import com.example.group.UserInsert;
import com.example.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class Book extends BaseModel {

    @ApiModelProperty("书籍id")
    @Null(groups = {Insert.class, UserInsert.class}, message = "书籍id必须为空")
    @NotNull(groups = {Update.class}, message = "书籍id不能为空")
    private Integer id;
    @ApiModelProperty("书籍名称")
    @NotNull(groups = {Insert.class, Update.class, UserInsert.class}, message = "书籍名称不能为空")
    private String bookName;
    @ApiModelProperty("用户id")
    private Integer userId;

}
