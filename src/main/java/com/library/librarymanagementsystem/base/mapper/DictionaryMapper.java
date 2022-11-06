package com.library.librarymanagementsystem.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.library.librarymanagementsystem.entity.Dictionary;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DictionaryMapper extends BaseMapper<Dictionary> {
    @Select("select * from dictionary where dic_key = #{key} ")
    List<Dictionary> getByKey(String key);
}
