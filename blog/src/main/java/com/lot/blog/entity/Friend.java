package com.lot.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: blog
 * @description: 友人
 * @author: lotsehsin
 * @create: 2022-02-26 00:47
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Friend {
    private Long id;
    private String blogname;
    private String blogaddress;
    private String pictureaddress;
    private Date createTime;
}
