package com.lot.blog.service.impl;

import com.lot.blog.dao.BlogDao;
import com.lot.blog.dao.CommentDao;
import com.lot.blog.entity.Blog;
import com.lot.blog.entity.Comment;
import com.lot.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: blog
 * @description: 评论业务
 * @author: lotsehsin
 * @create: 2022-03-18 10:16
 **/

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private BlogDao blogDao;

    //存放子评论的集合
    List<Comment> tempReplys = new ArrayList<>();

    @Override
    public int saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        blogDao.updateCommentCounts(comment.getBlog().getId());
        return commentDao.saveComment(comment);
    }

    @Override
    public int deleteComment(Long id) {
        blogDao.updateCommentCounts(id);
        return commentDao.deleteComment(id);
    }

    @Override
    public List<Comment> getCommentByBlogId(Long blogId) {
        //查询该博客下所有评论
        List<Comment> comments = commentDao.findByBlogIdParentIdNull(blogId,Long.parseLong("-1"));
        for(Comment comment : comments){
            Long id = comment.getId();
            String parentNickname1 = comment.getNickname();
            List<Comment> childComments = commentDao.findParentCommend(blogId,id);
            // 查询出子评论
            combineChildren(blogId, childComments, parentNickname1);
            comment.setReplyComment(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return comments;
    }

    private void combineChildren(Long blogId, List<Comment> childComments, String parentNickname1) {
//        判断是否有一级子评论
        if(childComments.size() > 0){
//                循环找出子评论的id
            for(Comment childComment : childComments){
                String parentNickname = childComment.getNickname();
                childComment.setParentNickname(parentNickname1);
                tempReplys.add(childComment);
                Long childId = childComment.getId();
//                    查询出子二级评论
                recursively(blogId, childId, parentNickname);
            }
        }
    }

    private void recursively(Long blogId, Long childId, String parentNickname1) {
//        根据子一级评论的id找到子二级评论
        List<Comment> replayComments = commentDao.findChildrenCommend(blogId,childId);

        if(replayComments.size() > 0){
            for(Comment replayComment : replayComments){
                String parentNickname = replayComment.getNickname();
                replayComment.setParentNickname(parentNickname1);
                Long replayId = replayComment.getId();
                tempReplys.add(replayComment);
                recursively(blogId,replayId,parentNickname);
            }
        }
    }
}
