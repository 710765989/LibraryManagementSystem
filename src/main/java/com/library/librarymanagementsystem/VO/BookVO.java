package com.library.librarymanagementsystem.VO;

import com.library.librarymanagementsystem.entity.Borrow;
import lombok.Data;

/**
 * 图书VO
 */
@Data
public class BookVO {
    /**
     * 图书id
     */
    private Integer id;
    /**
     * 书名
     */
    private String name;
    /**
     * 借阅状态
     */
    private String status;
    /**
     * 借阅状态·文本
     */
    private String statusText;
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
    /**
     * 类型·文本
     */
    private String typeText;
    /**
     * 删除标识
     */
    private String delFlag;
}
