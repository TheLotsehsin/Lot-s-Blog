package com.lot.blog.service.impl;

import com.lot.blog.dao.BlogDao;
import com.lot.blog.entity.Blog;
import com.lot.blog.entity.Tag;
import com.lot.blog.hander.NotFoundException;
import com.lot.blog.queryEntity.*;
import com.lot.blog.service.BlogService;
import com.lot.blog.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @program: blog
 * @description: Blog业务层
 * @author: lotsehsin
 * @create: 2022-03-04 11:54
 **/
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;

    @Override
    public List<Blog> getAllBlog() {
        return blogDao.getAllBlog();
    }

    @Override
    public ShowBlog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    @Override
    public List<BlogQuery> getAllBlogQuery() {
        return blogDao.getAllBlogQuery();
    }

    @Override
    public List<BlogQuery> getBlogByTitleOrTypeOrRecommend(SearchBlog searchblog) {
        return blogDao.getBlogByTitleOrTypeOrRecommend(searchblog);
    }

    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blog.setCommentCount(0);
        blogDao.saveBlog(blog);

        //保存博客后才能获取自增的id
        Long id = blog.getId();
        //保存博客后才可以获得自增的id
        List<Tag> tags = blog.getTags();
        for (Tag tag:
             tags) {
            blogDao.saveBlogAndTag(id,tag.getId());
        }
        return 1;
    }

    @Override
    public int updateBlog(Blog blog) {
        List<Tag> tags = blog.getTags();
        blog.setUpdateTime(new Date());
        blogDao.deleteBlogAndTag(blog.getId());
        for (int i = 0; i < tags.size(); i++) {
            Tag tag = tags.get(i);
            if (tag == null)
                continue;
            blogDao.saveBlogAndTag(blog.getId(),tag.getId());
        }
        return blogDao.updateBlog(blog);
    }

    @Override
    public int deleteBlog(Long id) {
        blogDao.deleteBlogAndTag(id);
        return blogDao.deleteBlog(id);
    }

    @Override
    public List<IndexPageBlog> getAllIndexPageBlog() {
        return blogDao.getAllIndexPageBlog();
    }

    @Override
    public List<IndexPageBlog> getIndexPageBlogByTitleOrContent(String query) {
        return blogDao.getIndexPageBlogByTitleOrContent(query);
    }

    @Override
    public List<IndexPageBlog> getIndexBlogByTagId(Long TagId) {
        return blogDao.getIndexBlogByTagId(TagId);
    }

    @Override
    public List<IndexPageBlog> getIndexBlogByTypeId(Long TypeId) {
        return blogDao.getIndexBlogByTypeId(TypeId);
    }

    @Override
    public List<ReCommendBlog> getAllReCommendBlog() {
        return blogDao.getAllReCommendBlog();
    }

    @Override
    public DetailBlog getDetailedBlog(Long id) {
        DetailBlog detailedBlog = blogDao.getDetailedBlog(id);
        if (detailedBlog == null){
            throw new NotFoundException("该博客不存在！");
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        blogDao.updateViews(id);
        blogDao.updateCommentCounts(id);
        return detailedBlog;
    }

    @Override
    public int countBlog() {
        return blogDao.countBlog();
    }

    @Override
    public int countComments() {
        return blogDao.countComments();
    }

    @Override
    public int sumViews() {
        return blogDao.sumViews();
    }

    //博客归档
    @Override
    public Map<String,Object> getBlogByTime(){
        List<String> years = blogDao.findGroupYear();
        /*去掉重复的时间*/
        Set<String> yearSet = new HashSet<>(years);
        Map<String,Object> map = new HashMap<>();
        for (String year : yearSet){
           map.put(year, blogDao.findBlogByYear(year));
        }
        return map;
    }
}
