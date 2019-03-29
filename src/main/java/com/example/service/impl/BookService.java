package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.BookMapper;
import com.example.model.po.Book;
import com.example.service.IBookService;
import org.springframework.stereotype.Service;

@Service
public class BookService extends ServiceImpl<BookMapper, Book> implements IBookService {
}
