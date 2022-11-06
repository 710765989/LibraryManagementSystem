package com.library.librarymanagementsystem.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.library.librarymanagementsystem.base.mapper.DictionaryMapper;
import com.library.librarymanagementsystem.base.service.DictionaryService;
import com.library.librarymanagementsystem.entity.Dictionary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {
    @Override
    public List<Dictionary> getByKey(String key) {
        return this.baseMapper.getByKey(key);
    }
}
