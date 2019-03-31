package com.example.model.vo;

import com.example.model.po.Book;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("书籍")
public class BookVO extends Book {
}
