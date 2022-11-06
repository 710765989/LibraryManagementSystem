package com.library.librarymanagementsystem.utils;

import com.library.librarymanagementsystem.base.service.impl.DictionaryServiceImpl;
import com.library.librarymanagementsystem.entity.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 词典存入本地缓存
 */
@Component
public class LocalCache {
    @Autowired
    private DictionaryServiceImpl dictionaryService;
    // 字典缓存对象
    public static volatile Map<String, Map<String, Dictionary>> cache;

    /**
     * 初始化
     */
    public void init() {
        if (cache == null) {
            synchronized (Dictionary.class) {
                if (cache == null) {
                    String[] keys = {DicConstant.BOOK_STATUS, DicConstant.BOOK_TYPE};
                    Map<String, Map<String, Dictionary>> cacheMap = new ConcurrentHashMap<>();
                    for (String key : keys) {
                        List<Dictionary> dicList = dictionaryService.getByKey(key);
                        Map<String, Dictionary> map = new ConcurrentHashMap<>();
                        for (Dictionary dic : dicList) {
                            map.put(dic.getValue(), dic);
                        }
                        cacheMap.put(key, map);
                    }
                    cache = cacheMap;
                }
            }
        }
    }

    /**
     * 从缓存中取得map
     */
    public Map<String, Dictionary> getDicMap(String key) {
        init();
        return cache != null ? cache.get(key) : null;
    }

    /**
     * 从缓存中取得字典对象
     */
    public Dictionary getDic(String key, String value) {
        Map<String, Dictionary> dicMap = getDicMap(key);
        if (dicMap == null) return null;
        Dictionary dictionary = dicMap.get(value);
        return dictionary != null && Constant.YES.equals(dictionary.getUseFlag()) ? dictionary : null;
    }

    /**
     * 从缓存中取得对应文本
     */
    public String getText(String key, String value) {
        Dictionary dic = getDic(key, value);
        return dic != null ? dic.getText() : null;
    }
}
