package com.library.librarymanagementsystem.utils;

import com.library.librarymanagementsystem.base.service.impl.DictionaryServiceImpl;
import com.library.librarymanagementsystem.domian.entity.Dictionary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 词典存入本地缓存
 */
@Component
@RequiredArgsConstructor
public class LocalCache {
    private final DictionaryServiceImpl dictionaryService;
    /**
     * 字典缓存对象
     */
    private static volatile Map<String, Dictionary> cache;

    /**
     * 初始化
     */
    public void init() {
        if (cache == null) {
            synchronized (Dictionary.class) {
                if (cache == null) {
                    // 查询词典表
                    List<Dictionary> allDic = dictionaryService.lambdaQuery().eq(Dictionary::getUseFlag, Constant.YES).list();
                    cache = allDic.stream().collect(Collectors.toConcurrentMap(Dictionary::getMapKey, Function.identity()));
                }
            }
        }
    }

    /**
     * 组合缓存key [dic_key:value]
     * @param key dic_key
     * @param value value
     * @return
     */
    private String getMapKey(String key, String value) {
        return key + ":" + value;
    }

    /**
     * 从缓存中取得字典对象
     */
    public Dictionary getDic(String key, String value) {
        init();
        return cache != null ? cache.get(getMapKey(key, value)) : null;
    }

    /**
     * 从缓存中取得对应文本
     */
    public String getText(String key, String value) {
        Dictionary dic = getDic(key, value);
        return dic != null ? dic.getText() : null;
    }

    private static volatile Map<String, List<Dictionary>> dicCache;

    /**
     * 根据key获取字典列表
     *
     * @param key 字典key
     * @return 字典列表
     */
    public List<Dictionary> getDicList(String key) {
        if (dicCache == null) {
            synchronized (Dictionary.class) {
                if (dicCache == null) {
                    dicCache = new ConcurrentHashMap<>();
                }
            }
        }
        List<Dictionary> list = dicCache.get(key);
        if (list == null || list.size() == 0) {
            list = dictionaryService.lambdaQuery().eq(Dictionary::getDicKey, key).eq(Dictionary::getUseFlag, Constant.YES).list();
            if (list != null && list.size() > 0) {
                dicCache.put(key, list);
            }
        }
        return list;
    }
}
