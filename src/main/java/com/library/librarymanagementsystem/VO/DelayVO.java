package com.library.librarymanagementsystem.VO;

import com.library.librarymanagementsystem.entity.Book;
import com.library.librarymanagementsystem.entity.Borrow;
import lombok.Data;

/**
* 延期归还VO
*/
@Data
public class DelayVO {
    /**
     * 延期归还id
     */
    private Integer id;
    /**
    * 用户id
    */
    private Integer userId;
    /**
     * 用户名
     */
    private Integer userName;
    /**
    * 图书id
    */
    //private Integer bookId;
    ///**
    // * 图书名
    // */
    //private String bookName;
    private Book book;
    /**
    * 借阅id
    */
    //private Integer borrowId;
    private BorrowVO borrow;
    /**
    * 延期天数
    */
    private Integer time;
}
