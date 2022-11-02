package com.library.librarymanagementsystem.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.library.librarymanagementsystem.base.mapper.BorrowMapper;
import com.library.librarymanagementsystem.base.service.BorrowService;
import com.library.librarymanagementsystem.entity.Borrow;
import org.springframework.stereotype.Service;

@Service
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, Borrow> implements BorrowService {
}
