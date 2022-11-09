package com.library.librarymanagementsystem.controller;

import com.library.librarymanagementsystem.domian.vo.BookVO;
import com.library.librarymanagementsystem.base.service.impl.BookServiceImpl;
import com.library.librarymanagementsystem.domian.entity.Book;
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

@RequestMapping("management")
@RestController
public class BooksController {
    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    private LocalCache localCache;

    /**
     * 书籍列表
     * @param vo 搜索书名
     */
    @GetMapping("list")
    public R info (BookVO vo) {
        //User currentUser = ShiroUtils.getCurrentUser();
        //if (currentUser == null) {
        //    currentUser = userService.getByUsername("admin");
        //}

        List<Book> list = bookService.getList(vo);
        //if (vo == null) {
        //    list = bookService.list();
        //} else {
        //    list = bookService.lambdaQuery().like(Book::getName, vo.getName()).list();
        //}
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
        Book byId = bookService.getById(book.getId());
        String delFlag = Constant.YES.equals(book.getDelFlag()) ? Constant.NO : Constant.YES;
        byId.setDelFlag(delFlag);
        bookService.updateById(byId);
        return R.ok();
    }
}
