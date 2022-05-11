package com.lot.blog.dao;

import com.lot.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* Description: 用户
* date: 2022/2/27 22:41
* @author: lotsehsin
* @since JDK 11
*/
@Mapper
@Repository
public interface UserDao {

    //管理员用户登录
    User checkUser(@Param("username") String username,@Param("password") String password);
}
