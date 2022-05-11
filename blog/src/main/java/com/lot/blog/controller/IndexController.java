package com.lot.blog.controller;

/**
* Description: 首页控制类
* date: 2022/3/28 19:08
* @author: lotsehsin
* @since JDK 11
*/
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lot.blog.entity.Blog;
import com.lot.blog.entity.Comment;
import com.lot.blog.entity.Tag;
import com.lot.blog.entity.Type;
import com.lot.blog.queryEntity.BlogQuery;
import com.lot.blog.queryEntity.DetailBlog;
import com.lot.blog.queryEntity.IndexPageBlog;
import com.lot.blog.queryEntity.ReCommendBlog;
import com.lot.blog.service.BlogService;
import com.lot.blog.service.CommentService;
import com.lot.blog.service.TagService;
import com.lot.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.List;


/*测试类--*/
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String index(@RequestParam(required = false,value = "pageNum",defaultValue = "1") int pagenum
            , Model model){
        PageHelper.startPage(pagenum,5);
        List<IndexPageBlog> blogs = blogService.getAllIndexPageBlog();
        List<Type> types = typeService.getIndexType();
        List<Tag> tags = tagService.getIndexTag();
        List<ReCommendBlog> reCommendBlogs = blogService.getAllReCommendBlog();
        //分页对象查询结果
        PageInfo<IndexPageBlog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("reCommendBlogs",reCommendBlogs);
        model.addAttribute("types",types);
        model.addAttribute("tags",tags);
        model.addAttribute("pageInfo",pageInfo);
        return "index";
    }

    //博客详情
    @GetMapping("/blog/{id}")
    public String enterBlog(@PathVariable Long id, Model model){
        DetailBlog blog = blogService.getDetailedBlog(id);
        List<Comment> comments = commentService.getCommentByBlogId(id);
        model.addAttribute("comments",comments);
        model.addAttribute("blog",blog);
        return "blog";
    }

    //搜索博客
    @RequestMapping("/search")
    public String blog(@RequestParam(required = false,value = "pageNum",defaultValue = "1") int pagenum,
            @RequestParam("query") String query, Model model){
        System.out.println("查询信息为==="+query);
        PageHelper.startPage(pagenum,8);
        List<IndexPageBlog> blogs = blogService.getIndexPageBlogByTitleOrContent(query);
        PageInfo<IndexPageBlog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

}
