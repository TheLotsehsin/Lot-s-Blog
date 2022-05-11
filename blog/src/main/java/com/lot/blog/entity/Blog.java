package com.lot.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* Description: 博客类
* date: 2022/2/25 11:59
* @author: lotsehsin
* @since JDK 11
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    private Integer commentCount;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentabled;
    private boolean published;
    private boolean recommend;
    private Date createTime;
    private Date updateTime;
    private String description;

    //mybatis属性查询
    private Long userId;
    private Long typeId;
    private String tagIds;

    //多对一
    //每个博客都有一个标签
    private Comment comment;
    //每个博客都由一个用户发布
    private User user;
    //每个博客有一个类
    private Type type;

    //一对多
    //每个博客下有多个标签
    private List<Tag> tags = new ArrayList<>();
    //每个博客下有多个顶级评论
    private List<Comment> comments = new ArrayList<>();


    //初始化得到tagIds的集合，一会要用到
    public void init(){
        this.tagIds = tagsToIds(this.getTags());
    }

    //将tags集合转换为tagIds字符串形式：“1,2,3”,用于编辑博客时显示博客的tag
    private String tagsToIds(List<Tag> tags) {
        if(!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for(Tag tag: tags) {
                if(flag)
                    ids.append(",");
                else
                    flag = true;
                ids.append(tag.getId());
            }
            return ids.toString();
        }
        else
            return tagIds;
    }

}
