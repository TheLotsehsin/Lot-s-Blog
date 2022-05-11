package com.lot.blog.queryEntity;

import com.lot.blog.entity.Type;
import com.lot.blog.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: blog
 * @description: 首页博客展示信息
 * @author: lotsehsin
 * @create: 2022-03-14 00:38
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndexPageBlog {
    private Long id;
    private String title;
    private Integer views;
    private String firstPicture;
    private Date updateTime;
    private Integer commentCount;
    private String description;

    private String avatar;
    private String nickname;

    private Long typeId;
    private String typeName;

}
