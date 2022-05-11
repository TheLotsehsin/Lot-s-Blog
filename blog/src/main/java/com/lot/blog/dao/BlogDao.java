package com.lot.blog.dao;

import com.lot.blog.entity.Blog;
import com.lot.blog.queryEntity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* Description: Blog访问层
* date: 2022/3/4 11:51
* @author: lotsehsin
* @since JDK 11
*/
@Repository
@Mapper
public interface BlogDao {

    List<Blog> getAllBlog();

    ShowBlog getBlogById(Long id);

    List<BlogQuery> getBlogByTitleOrTypeOrRecommend(SearchBlog searchblog);

    List<BlogQuery> getAllBlogQuery();

    List<IndexPageBlog> getAllIndexPageBlog();

    List<IndexPageBlog> getIndexBlogByTagId(Long TagId);

    List<IndexPageBlog> getIndexBlogByTypeId(Long TypeId);

    List<IndexPageBlog> getIndexPageBlogByTitleOrContent(String query);

    List<ReCommendBlog> getAllReCommendBlog();

    DetailBlog getDetailedBlog(@Param("id") Long id);

    int saveBlog(Blog blog);

    int saveBlogAndTag(Long blogId,Long tagId);

    int updateBlog(Blog blog);

    int deleteBlog(Long id);

    int deleteBlogAndTag(Long blogId);

    int updateViews(Long id);

    int updateCommentCounts(Long id);

    int countBlog();

    int countComments();

    int sumViews();

    List<String> findGroupYear();

    List<Blog> findBlogByYear(String updateTime);

}
