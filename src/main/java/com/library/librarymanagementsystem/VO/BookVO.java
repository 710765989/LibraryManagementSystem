package com.library.librarymanagementsystem.VO;

import com.library.librarymanagementsystem.entity.Book;
import com.library.librarymanagementsystem.entity.Borrow;
import com.library.librarymanagementsystem.enums.BookStatus;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * bookVO
 */
@Data
public class BookVO {
    private Integer id;
    /**
     * 书名
     */
    private String name;

    /**
     * 借阅状态
     */
    //private BookStatus status;
    private String status;
    /**
     * 借阅状态·文本
     */
    private String value;

    /**
     * 作者
     */
    private String author;

    /**
     * 借阅信息
     */
    private Borrow borrow;

    /**
     * 类型
     */
    private String type;
}
