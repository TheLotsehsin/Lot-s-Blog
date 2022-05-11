package com.lot.blog.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* Description: 评论类
* date: 2022/2/25 12:00
* @author:lotsehsin
* @since JDK 11
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Long id;
    private String nickname;
    private String email;
    private String avatar;
    private String content;
    private Date createTime;
    private Long blogId;

    private Long parentCommentId;
    private Comment parentComment;
    private String parentNickname;

    private Blog blog;

    private boolean adminComment;

    private List<Comment> replyComment = new ArrayList<>();
}
