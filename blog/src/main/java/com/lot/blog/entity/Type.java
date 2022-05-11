package com.lot.blog.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
* Description: 博客类型类
* date: 2022/2/25 12:01
* @author:lotsehsin
* @since JDK 11
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {
    private Long id;
    private String name;

    private List<Blog> blog = new ArrayList<>();
}
