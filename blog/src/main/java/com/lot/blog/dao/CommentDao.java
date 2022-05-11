package com.lot.blog.dao;

import com.lot.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentDao {

   //根据创建时间倒序排列
    List<Comment> findByBlogIdParentIdNull(@Param("blogId") Long blogId, @Param("blogParentId") Long blogParentId);
    //查询一级回复
    List<Comment> findParentCommend(@Param("blogId") Long blogId,@Param("id") Long id);
    //查看二级回复
    List<Comment> findChildrenCommend(@Param("blogId") Long blogId,@Param("childId") Long childId);
    //上传评论
    int saveComment(Comment comment);
    //删除评论
    int deleteComment(Long id);


}
