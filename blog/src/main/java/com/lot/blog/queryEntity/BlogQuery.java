package com.lot.blog.queryEntity;

import com.lot.blog.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: blog
 * @description: 博客后台数据显示
 * @author: lotsehsin
 * @create: 2022-03-05 21:23
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogQuery {
    private Long id;
    private String title;
    private Type type;
    private boolean published;
    private boolean recommend;
    private Date updateTime;
    private Long typeId;
}
