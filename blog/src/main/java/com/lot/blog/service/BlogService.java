package com.lot.blog.service;

import com.lot.blog.dao.BlogDao;
import com.lot.blog.entity.Blog;
import com.lot.blog.queryEntity.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public interface BlogService {
    List<Blog> getAllBlog();

    ShowBlog getBlogById(Long id);

    List<BlogQuery> getBlogByTitleOrTypeOrRecommend(SearchBlog searchblog);

    List<BlogQuery> getAllBlogQuery();

    List<IndexPageBlog> getAllIndexPageBlog();

    List<IndexPageBlog> getIndexBlogByTagId(Long TagId);

    List<IndexPageBlog> getIndexBlogByTypeId(Long TypeId);

    List<IndexPageBlog> getIndexPageBlogByTitleOrContent(String query);

    DetailBlog getDetailedBlog(Long id);

    List<ReCommendBlog> getAllReCommendBlog();

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlog(Long id);

    int countBlog();

    int countComments();

    int sumViews();

    Map<String,Object> getBlogByTime();
}
