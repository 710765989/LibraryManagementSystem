package com.library.librarymanagementsystem.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.library.librarymanagementsystem.domian.vo.BorrowVO;
import com.library.librarymanagementsystem.domian.vo.DelayVO;
import com.library.librarymanagementsystem.base.service.impl.BookServiceImpl;
import com.library.librarymanagementsystem.base.service.impl.BorrowServiceImpl;
import com.library.librarymanagementsystem.base.service.impl.DelayServiceImpl;
import com.library.librarymanagementsystem.base.service.impl.UserServiceImpl;
import com.library.librarymanagementsystem.domian.entity.Book;
import com.library.librarymanagementsystem.domian.entity.Borrow;
import com.library.librarymanagementsystem.domian.entity.Delay;
import com.library.librarymanagementsystem.domian.entity.User;
import com.library.librarymanagementsystem.utils.Constant;
import com.library.librarymanagementsystem.utils.R;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("delay")
@RestController
@RequiredArgsConstructor
public class DelayController {
    private final DelayServiceImpl delayService;
    private final BookServiceImpl bookService;
    private final BorrowServiceImpl borrowService;
    private final UserServiceImpl userService;

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

    /**
     * 借阅延期
     */
    @Transactional
    @PostMapping("{id}")
    public R delay(@PathVariable String id) {
        if (StringUtils.isBlank(id)) return R.error("借阅id不存在");
        Borrow borrow = borrowService.getById(id);
        Delay delay = new Delay();
        User admin = userService.getByUsername("admin");
        delay.setUserId(admin.getId());
        delay.setBookId(borrow.getBookId());
        delay.setBorrowId(borrow.getId());
        delay.setTime(Constant.DELAY_DAYS);
        delayService.updateById(delay);
        return R.ok();
    }
}
