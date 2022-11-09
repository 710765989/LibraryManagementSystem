package com.library.librarymanagementsystem.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.library.librarymanagementsystem.domian.entity.Borrow;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BorrowMapper extends BaseMapper<Borrow> {
}
