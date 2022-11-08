package com.library.librarymanagementsystem.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.library.librarymanagementsystem.entity.Borrow;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BorrowMapper extends BaseMapper<Borrow> {
}
