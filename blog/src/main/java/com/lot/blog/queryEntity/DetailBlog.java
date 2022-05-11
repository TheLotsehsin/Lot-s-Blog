package com.lot.blog.queryEntity;

import com.lot.blog.entity.Tag;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: blog
 * @description: 博客信息详情
 * @author: lotsehsin
 * @create: 2022-03-15 02:49
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailBlog {
    private Long id;
    private String firstPicture;
    private String flag;
    private String title;
    private String content;

    private Integer commentCount;
    private Integer views;
    private Date createTime;
    private Date updateTime;
    private boolean commentabled;
    private boolean shareStatement;
    private boolean appreciation;
    private String nickname;
    private String avatar;
    private String typeName;

    private List<Tag> tags = new ArrayList<>();
}
