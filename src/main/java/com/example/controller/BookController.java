package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.model.po.Book;
import com.example.model.vo.BookVO;
import com.example.model.vo.ResultVO;
import com.example.service.IBookService;
import com.example.util.ModelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import static com.example.model.vo.ResultVO.SUCCESS;

@RestController
@RequestMapping(value = "/book", produces = "application/json; charset=UTF-8")
@Api(tags = "书籍")
public class BookController {

    @Autowired
    private IBookService bookService;

    @GetMapping("/{current}/{size}")
    @ApiOperation(value = "查询书籍列表")
    public ResultVO list(@PathVariable @NotNull(message = "当前页不能为空") @ApiParam(value = "当前页", defaultValue = "1", required = true) long current,
                         @PathVariable @NotNull(message = "每页显示条数不能为空") @ApiParam(value = "当前页", defaultValue = "10", required = true) long size,
                         BookVO bookVO) {
        Page<Book> page = new Page<>(current, size);
        Wrapper<Book> wrapper = new QueryWrapper<>(bookVO);
        IPage<Book> iPage = bookService.page(page, wrapper);
        IPage books = (IPage) ModelUtil.copy(iPage, new ModelUtil.Mapping(Book.class, BookVO.class));
        return new ResultVO<>(SUCCESS, "", books);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询书籍")
    public ResultVO<BookVO> findById(@PathVariable @NotNull(message = "书籍id不能为空") @ApiParam Integer id) {
        Book book = bookService.getById(id);
        BookVO bookVO = (BookVO) ModelUtil.copy(book, new ModelUtil.Mapping(Book.class, BookVO.class));
        return new ResultVO<>(SUCCESS, "", bookVO);
    }

}
