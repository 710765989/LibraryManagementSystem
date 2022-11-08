package com.library.librarymanagementsystem.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.library.librarymanagementsystem.entity.Delay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DelayMapper extends BaseMapper<Delay> {
    @Select("select * from delay where borrow_id = #{borrowId}")
    Delay getDelayByBorrowId(Integer borrowId);
}
