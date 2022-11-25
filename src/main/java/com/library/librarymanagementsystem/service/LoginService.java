package com.library.librarymanagementsystem.service;

import com.library.librarymanagementsystem.domian.entity.User;

import java.security.NoSuchAlgorithmException;

public interface LoginService {
    /**
     * 用户登录
     * @param user 登录信息
     */
    String login(User user) throws NoSuchAlgorithmException;
}
