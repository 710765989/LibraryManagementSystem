package com.library.librarymanagementsystem.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.library.librarymanagementsystem.base.mapper.BookMapper;
import com.library.librarymanagementsystem.base.service.BookService;
import com.library.librarymanagementsystem.entity.Book;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
}
