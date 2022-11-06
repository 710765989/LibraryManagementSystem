package com.library.librarymanagementsystem.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.library.librarymanagementsystem.VO.BookVO;
import com.library.librarymanagementsystem.entity.Book;

import java.util.List;

public interface BookService extends IService<Book> {
    List<Book> getList(BookVO vo);
}
