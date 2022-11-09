package com.library.librarymanagementsystem.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.library.librarymanagementsystem.domian.vo.BookVO;
import com.library.librarymanagementsystem.domian.entity.Book;

import java.util.List;

public interface BookService extends IService<Book> {
    List<Book> getList(BookVO vo);
}
