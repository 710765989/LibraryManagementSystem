package com.library.librarymanagementsystem.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.library.librarymanagementsystem.base.mapper.UserMapper;
import com.library.librarymanagementsystem.base.service.UserService;
import com.library.librarymanagementsystem.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User getByUsername(String username) {
        return this.baseMapper.getByUsername(username);
    }
}
