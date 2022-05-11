package com.lot.blog.queryEntity;

import lombok.*;

/**
 * @program: blog
 * @description: 推荐博客类
 * @author: lotsehsin
 * @create: 2022-03-14 19:22
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReCommendBlog {
    private Long id;
    private String title;
    private boolean recommend;
}
