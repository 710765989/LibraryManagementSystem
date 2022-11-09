package com.library.librarymanagementsystem.controller;

import com.library.librarymanagementsystem.domian.entity.Dictionary;
import com.library.librarymanagementsystem.utils.LocalCache;
import com.library.librarymanagementsystem.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("dictionary")
@RestController
public class DictionaryController {
    @Autowired
    private LocalCache localCache;

    /**
     * 获取字典
     */
    @GetMapping("list")
    public R getDicList(String key) {
        List<Dictionary> list = localCache.getDicList(key);
        if (list != null && list.size() > 0) {
            return R.ok().put("data", list);
        } else {
            return R.error("字典数据不存在");
        }
    }
}
