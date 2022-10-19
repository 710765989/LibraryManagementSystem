package com.library.librarymanagementsystem.utils;

/**
 * 常量类
 */
public class Constant {
    /**
     * 借阅状态 0:未借出 1:已借出
     * book.status
     */
    public static final String BOOK_STATUS_0 = "0";
    public static final String BOOK_STATUS_1 = "1";

    /**
     * 用户类型 0:管理员 1:读者
     * user.type
     */
    public static final String USER_TYPE_MANAGER = "0";
    public static final String USER_TYPE_READER = "1";

    /**
     * 归还状态 0:已归还 1:未归还
     */
    public static final String BORROW_STATUS_0 = "0";
    public static final String BORROW_STATUS_1 = "1";

    public static final String ADMIN_TOKEN = "admin-token";
    public static final String USER_TOKEN = "user-token";
}
