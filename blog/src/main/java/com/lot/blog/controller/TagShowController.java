package com.lot.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lot.blog.entity.Tag;
import com.lot.blog.entity.Type;
import com.lot.blog.queryEntity.IndexPageBlog;
import com.lot.blog.service.BlogService;
import com.lot.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @program: blog
 * @description: 前端标签显示
 * @author: lotsehsin
 * @create: 2022-03-28 23:42
 **/
@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @RequestMapping("/tags/{id}")
    public String showTags(@PathVariable Long id, Model model,
                           @RequestParam(value = "pageNum",required = false,defaultValue = "1") int pagenum){
        PageHelper.startPage(pagenum,25);
        List<Tag> tags = tagService.getIndexTag1();
        if (id == -1){
            id = tags.get(0).getId();
        }
        List<IndexPageBlog> blogs = blogService.getIndexBlogByTagId(id);
        PageInfo<IndexPageBlog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("tags",tags);
        model.addAttribute("currentId",id);
        return "tags";
    }
}
