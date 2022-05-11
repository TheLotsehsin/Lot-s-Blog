package com.lot.blog.service;

import com.lot.blog.entity.User;

/**
* Description: 用户业务接口
* date: 2022/2/27 22:40
* @author: lotsehsin
* @since JDK 11
*/
public interface UserService {

    User checkUser(String username, String password);

}
