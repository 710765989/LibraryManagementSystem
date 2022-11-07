package com.library.librarymanagementsystem.controller;

import com.library.librarymanagementsystem.VO.BookVO;
import com.library.librarymanagementsystem.base.service.impl.BookServiceImpl;
import com.library.librarymanagementsystem.base.service.impl.BorrowServiceImpl;
import com.library.librarymanagementsystem.base.service.impl.UserServiceImpl;
import com.library.librarymanagementsystem.entity.Book;
import com.library.librarymanagementsystem.entity.Borrow;
import com.library.librarymanagementsystem.entity.User;
import com.library.librarymanagementsystem.utils.Constant;
import com.library.librarymanagementsystem.utils.DicConstant;
import com.library.librarymanagementsystem.utils.LocalCache;
import com.library.librarymanagementsystem.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("borrow")
@RestController
public class BorrowController {
    @Autowired
    private BorrowServiceImpl borrowService;
    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private LocalCache localCache;

    /**
     * 借阅图书
     *
     * @param borrow 借阅
     */
    @Transactional
    @PostMapping("save")
    public R edit(@RequestBody @Valid Borrow borrow) {
        User admin = userService.getByUsername("admin");
        borrow.setUserId(admin.getId());
        borrow.setStatus(Constant.BORROW_STATUS_1);
        borrowService.updateById(borrow);

        Book book = bookService.getById(borrow.getBookId());
        book.setStatus(Constant.BOOK_STATUS_1);
        bookService.updateById(book);
        return R.ok();
    }
}
