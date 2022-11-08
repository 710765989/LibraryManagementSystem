package com.library.librarymanagementsystem.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.library.librarymanagementsystem.entity.Delay;

public interface DelayService extends IService<Delay> {
    Delay getDelayByBorrowId(Integer borrowId);
}
