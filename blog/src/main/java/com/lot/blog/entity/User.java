package com.lot.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* Description: 用户类
* date: 2022/2/25 12:01
* @author:lotsehsin
* @since JDK 11
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private Integer type;
    private String avatar;
    private Date createTime;//TableField
    private Date updateTime;

    private List<Blog> blogList = new ArrayList<>();
}
