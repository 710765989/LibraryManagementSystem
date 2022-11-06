package com.library.librarymanagementsystem.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.library.librarymanagementsystem.entity.Dictionary;

import java.util.List;

public interface DictionaryService extends IService<Dictionary> {
    List<Dictionary> getByKey(String key);
}
