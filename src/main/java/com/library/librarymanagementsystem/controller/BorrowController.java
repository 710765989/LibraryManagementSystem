package com.library.librarymanagementsystem.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.library.librarymanagementsystem.VO.BookVO;
import com.library.librarymanagementsystem.VO.BorrowVO;
import com.library.librarymanagementsystem.base.service.impl.BookServiceImpl;
import com.library.librarymanagementsystem.base.service.impl.BorrowServiceImpl;
import com.library.librarymanagementsystem.base.service.impl.DelayServiceImpl;
import com.library.librarymanagementsystem.base.service.impl.UserServiceImpl;
import com.library.librarymanagementsystem.entity.Book;
import com.library.librarymanagementsystem.entity.Borrow;
import com.library.librarymanagementsystem.entity.Delay;
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
import java.time.LocalDateTime;
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
    private DelayServiceImpl delayService;
    @Autowired
    private LocalCache localCache;

    @GetMapping("list")
    public R list() {
        User admin = userService.getByUsername("admin");
        List<Borrow> list = borrowService.lambdaQuery().eq(Borrow::getUserId, admin.getId()).list();
        List<BorrowVO> res = new ArrayList<>();
        for (Borrow borrow : list) {
            BorrowVO vo = new BorrowVO();
            BeanUtils.copyProperties(borrow, vo);

            vo.setStatusText(localCache.getText(DicConstant.BORROW_TYPE, vo.getStatus()));

            LocalDateTime returnTime = borrow.getReturnTime();
            LocalDateTime realReturnTime = borrow.getRealReturnTime();
            String r = returnTime != null ? returnTime.toLocalDate().toString() : "";
            String rr = realReturnTime != null ? realReturnTime.toLocalDate().toString() : "";
            vo.setReturnTime(r);
            vo.setRealReturnTime(rr);

            Book book = bookService.getById(borrow.getBookId());
            vo.setBookId(book.getId());
            vo.setBookName(book.getName());

            // 是否已续借
            Delay delay = delayService.getDelayByBorrowId(borrow.getId());
            vo.setDelayFlag(delay == null ? Constant.NO : Constant.YES);
            res.add(vo);
        }
        return R.ok().put("data", res);
    }

    /**
     * 借阅图书
     *
     * @param borrow 借阅
     */
    @Transactional
    @PostMapping("save")
    public R edit(@RequestBody @Valid Borrow borrow) {
        if (LocalDateTime.now().plusMonths(3).isBefore(borrow.getReturnTime())) {
            return R.error("借阅时长能超过3个月");
        }
        User admin = userService.getByUsername("admin");
        borrow.setId(null);
        borrow.setUserId(admin.getId());
        borrow.setStatus(Constant.BORROW_STATUS_1);
        borrowService.save(borrow);

        Book book = bookService.getById(borrow.getBookId());
        book.setBorrowId(borrow.getId());
        book.setStatus(Constant.BOOK_STATUS_1);
        bookService.updateById(book);
        return R.ok();
    }

    /**
     * 归还图书
     */
    @Transactional
    @PostMapping("return/{id}")
    public R returnBook(@PathVariable String id) {
        if (StringUtils.isBlank(id)) return R.error("借阅id不存在");
        Borrow borrow = borrowService.getById(id);
        if (borrow != null) {
            borrow.setRealReturnTime(LocalDateTime.now());
            borrow.setStatus(Constant.BORROW_STATUS_0);
            borrowService.updateById(borrow);

            Book book = bookService.getById(borrow.getBookId());
            book.setBorrowId(0);
            book.setStatus(Constant.BOOK_STATUS_0);
            bookService.updateById(book);
            return R.ok();
        }
        return R.error("借阅id不存在");
    }
}
