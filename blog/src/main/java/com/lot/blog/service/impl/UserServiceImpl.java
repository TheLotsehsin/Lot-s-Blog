package com.lot.blog.service.impl;

import com.lot.blog.dao.UserDao;
import com.lot.blog.entity.User;
import com.lot.blog.service.UserService;
import com.lot.blog.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: blog
 * @description: 用户业务实现
 * @author: lotsehsin
 * @create: 2022-02-27 22:44
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        return userDao.checkUser(username, MD5Util.code(password));
    }
}
