package com.library.librarymanagementsystem.VO;

import lombok.Data;

/**
 * 图书借阅VO
 */
@Data
public class BorrowVO {
    /***
     * 借阅id
     */
    private Integer id;
    /**
     * 图书id
     */
    private Integer bookId;
    /**
     * 图书名
     */
    private String bookName;
    /**
     * 状态
     */
    private String status;
    /**
     * 状态文本
     */
    private String statusText;
    /**
     * 归还时间
     */
    private String returnTime;
    /**
     * 实际归还时间
     */
    private String realReturnTime;
}
