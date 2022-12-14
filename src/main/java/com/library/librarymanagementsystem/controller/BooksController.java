package com.library.librarymanagementsystem.controller;

import com.library.librarymanagementsystem.domian.vo.BookVO;
import com.library.librarymanagementsystem.base.service.impl.BookServiceImpl;
import com.library.librarymanagementsystem.domian.entity.Book;
import com.library.librarymanagementsystem.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RequestMapping("management")
@RestController
@RequiredArgsConstructor
public class BooksController {
    private final BookServiceImpl bookService;
    private final LocalCache localCache;

    /**
     * 书籍列表
     * @param vo 搜索书名
     */
    @GetMapping("list")
    public R info (BookVO vo) {
        List<Book> list = bookService.getList(vo);
        //PageHelper.startPage(1, 5);
        List<BookVO> vos = new ArrayList<>();
        list.forEach(l -> {
            BookVO bookVO = new BookVO();
            BeanUtils.copyProperties(l, bookVO);
            bookVO.setStatusText(localCache.getText(DicConstant.BOOK_STATUS, l.getStatus()));
            bookVO.setTypeText(localCache.getText(DicConstant.BOOK_TYPE, l.getType()));
            vos.add(bookVO);
        });

        Map<String, Object> data = new HashMap<>();
        data.put("items", vos);
        data.put("total", vos.size());
        return R.ok().put("data", data);
    }

    /**
     * 保存图书
     *
     * @param book 图书对象
     */
    @Transactional
    @PostMapping("save")
    public R edit(@RequestBody @Valid Book book) {
        if (Objects.isNull(ShiroUtils.getCurrentManager())) {
            return R.error("权限不足！");
        }
        bookService.saveOrUpdate(book);
        return R.ok();
    }

    /**
     * 逻辑删除/启用图书
     *
     * @param book 图书
     */
    @Transactional
    @PostMapping("enable")
    public R enable(@RequestBody Book book) {
        if (Objects.isNull(ShiroUtils.getCurrentManager())) {
            return R.error("权限不足！");
        }
        Book byId = bookService.getById(book.getId());
        String delFlag = Constant.YES.equals(book.getDelFlag()) ? Constant.NO : Constant.YES;
        byId.setDelFlag(delFlag);
        bookService.updateById(byId);
        return R.ok();
    }
}
