package com.lot.blog.queryEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: blog
 * @description: 后台查询博客信息
 * @author: lotsehsin
 * @create: 2022-03-05 21:35
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchBlog {
    private  String title;
    private Long typeId;
    private boolean recommend;
}
