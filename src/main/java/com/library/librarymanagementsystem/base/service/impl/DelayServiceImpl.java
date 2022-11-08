package com.library.librarymanagementsystem.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.library.librarymanagementsystem.base.mapper.DelayMapper;
import com.library.librarymanagementsystem.base.service.DelayService;
import com.library.librarymanagementsystem.entity.Delay;
import org.springframework.stereotype.Service;

@Service
public class DelayServiceImpl extends ServiceImpl<DelayMapper, Delay> implements DelayService {

    @Override
    public Delay getDelayByBorrowId(Integer borrowId) {
        return this.baseMapper.getDelayByBorrowId(borrowId);
    }
}
