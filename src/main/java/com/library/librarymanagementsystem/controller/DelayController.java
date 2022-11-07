package com.library.librarymanagementsystem.controller;

import com.library.librarymanagementsystem.VO.BorrowVO;
import com.library.librarymanagementsystem.VO.DelayVO;
import com.library.librarymanagementsystem.base.service.impl.BookServiceImpl;
import com.library.librarymanagementsystem.base.service.impl.BorrowServiceImpl;
import com.library.librarymanagementsystem.base.service.impl.DelayServiceImpl;
import com.library.librarymanagementsystem.base.service.impl.UserServiceImpl;
import com.library.librarymanagementsystem.entity.Book;
import com.library.librarymanagementsystem.entity.Borrow;
import com.library.librarymanagementsystem.entity.Delay;
import com.library.librarymanagementsystem.entity.User;
import com.library.librarymanagementsystem.utils.LocalCache;
import com.library.librarymanagementsystem.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("delay")
@RestController
public class DelayController {
    @Autowired
    private DelayServiceImpl delayService;
    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    private BorrowServiceImpl borrowService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private LocalCache localCache;

    /**
     * 延期记录
     */
    @GetMapping("list")
    public R list() {
        User admin = userService.getByUsername("admin");
        List<Delay> list = delayService.lambdaQuery().eq(Delay::getUserId, admin.getId()).list();
        List<DelayVO> res = new ArrayList<>();
        for (Delay delay : list) {
            DelayVO vo = new DelayVO();
            BeanUtils.copyProperties(delay, vo);

            Book book = bookService.getById(delay.getBookId());
            vo.setBook(book);

            Borrow borrow = borrowService.getById(delay.getBorrowId());
            BorrowVO borrowVO = new BorrowVO();
            BeanUtils.copyProperties(borrow, borrowVO);
            LocalDateTime returnTime = borrow.getReturnTime();
            LocalDateTime realReturnTime = borrow.getRealReturnTime();
            String r = returnTime != null ? returnTime.toLocalDate().toString() : "";
            String rr = realReturnTime != null ? realReturnTime.toLocalDate().toString() : "";
            borrowVO.setReturnTime(r);
            borrowVO.setRealReturnTime(rr);
            vo.setBorrow(borrowVO);
            res.add(vo);
        }
        return R.ok().put("data", res);
    }
}
