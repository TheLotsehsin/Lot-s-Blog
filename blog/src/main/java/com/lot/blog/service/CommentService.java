package com.lot.blog.service;

import com.lot.blog.entity.Comment;

import java.util.List;

public interface CommentService {

    //上传评论
    int saveComment(Comment comment);
    //删除评论
    int deleteComment(Long id);
    //按博客号查询评论信息
    List<Comment> getCommentByBlogId(Long blogId);
}
