package com.lot.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
* Description: 标签类
* date: 2022/2/25 12:00
* @author:lotsehsin
* @since JDK 11
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    private Long id;
    private String name;

    private List<Blog> blogList = new ArrayList<>();
}
