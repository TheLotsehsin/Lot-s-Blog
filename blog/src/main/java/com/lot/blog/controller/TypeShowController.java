package com.lot.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lot.blog.entity.Type;
import com.lot.blog.queryEntity.IndexPageBlog;
import com.lot.blog.service.BlogService;
import com.lot.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @program: blog
 * @description: 前端类型显示
 * @author: lotsehsin
 * @create: 2022-03-28 23:43
 **/
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @RequestMapping(value="/types/{id}")
    public String showTypes(@PathVariable Long id, Model model,
                           @RequestParam(value = "pageNum",required = false,defaultValue = "1") int pagenum){
        PageHelper.startPage(pagenum,25);
        List<Type> types = typeService.getIndexType1();
        if (id == -1){
            id = types.get(0).getId();
        }
        List<IndexPageBlog> blogs = blogService.getIndexBlogByTypeId(id);
        PageInfo<IndexPageBlog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("types",types);
        model.addAttribute("currentId",id);
        return "types";
    }
}
