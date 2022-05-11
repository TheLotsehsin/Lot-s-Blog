package com.lot.blog.controller;

import com.lot.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @program: blog
 * @description: 归档显示
 * @author: lotsehsin
 * @create: 2022-03-28 23:44
 **/

@Controller
public class ArchiveShowController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model){
        int countBlog = blogService.countBlog();
        Map<String, Object> map = blogService.getBlogByTime();
        model.addAttribute("countBlog",countBlog);
        model.addAttribute("map",map);
        return "archives";
    }
}
