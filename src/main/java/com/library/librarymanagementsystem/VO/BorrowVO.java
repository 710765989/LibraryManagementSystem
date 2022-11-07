package com.library.librarymanagementsystem.VO;

import lombok.Data;

@Data
public class BorrowVO {
    private Integer id;
    private Integer bookId;
    private String bookName;
    private String status;
    private String statusText;
    private String returnTime;
    private String realReturnTime;
}
