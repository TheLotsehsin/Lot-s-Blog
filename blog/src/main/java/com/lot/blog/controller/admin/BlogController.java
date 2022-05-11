package com.lot.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lot.blog.dao.BlogDao;
import com.lot.blog.entity.Blog;
import com.lot.blog.entity.Tag;
import com.lot.blog.entity.Type;
import com.lot.blog.entity.User;
import com.lot.blog.queryEntity.BlogQuery;
import com.lot.blog.queryEntity.SearchBlog;
import com.lot.blog.queryEntity.ShowBlog;
import com.lot.blog.service.BlogService;
import com.lot.blog.service.TagService;
import com.lot.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: blog
 * @description: 博客信息管理
 * @author: lotsehsin
 * @create: 2022-02-28 12:17
 **/
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String enterBlogs(@RequestParam(required = false,value = "pageNum",defaultValue = "1") int pagenum
            , Model model){
        //倒序排序
        String orderBy = "b.id desc";
        PageHelper.startPage(pagenum,6,orderBy);
        List<BlogQuery> allBlogQuery = blogService.getAllBlogQuery();
        List<Type> typeList = typeService.getAllType();
        //分页对象查询结果
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(allBlogQuery);
        model.addAttribute("types",typeList);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/adminBlogs";
    }

    /*跳转修改博客*/
    @GetMapping("/blogs/{id}/input")
    public String enterUpdateBlog(@PathVariable Long id,Model model){
        ShowBlog showBlog = blogService.getBlogById(id);
        List<Type> type = typeService.getAllType();
        List<Tag> tags = tagService.getAllTag();
        model.addAttribute("tags",tags);
        model.addAttribute("blog",showBlog);
        model.addAttribute("types",type);
        return "admin/blogsInput";
    }


    /*跳转新增博客*/
    @GetMapping("/blogs/input")
    public String enterInputBlog(Model model,HttpSession session){
        model.addAttribute("blog",new Blog());
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("tags",tagService.getAllTag());
        System.out.println("session====="+session.getAttribute("user"));
        System.out.println("type=="+typeService.getAllType()+"tags==="+tagService.getAllTag());
        return "admin/blogsInput";
    }

    /*删除博客*/
    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes attributes){
        int res = blogService.deleteBlog(id);
        if (res != 0){
            attributes.addFlashAttribute("message","博客删除成功！");
        }
        return "redirect:/admin/blogs";
    }

    //博客添加
    @PostMapping("/blogs")
    public String addBlogs(Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        System.out.println("user===="+session.getAttribute("user"));
        System.out.println("=====userId=="+blog.getUser().getId());
        blog.setUserId(blog.getUser().getId());
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTypeId(blog.getType().getId());
        blog.setTags(tagService.getTagsByString(blog.getTagIds()));
        int res = blogService.saveBlog(blog);
        if (res != 0){
            attributes.addFlashAttribute("message","恭喜，博客发布成功！");
        }else{
            attributes.addFlashAttribute("message","抱歉，博客发布失败！");
        }
        return "redirect:/admin/blogs";
    }

    /*修改博客*/
    @PostMapping("/blogs/{id}")
    public String updateBlog(@PathVariable Long id ,Blog blog,RedirectAttributes attributes,HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        System.out.println("user===="+session.getAttribute("user"));
        System.out.println("=====userId=="+blog.getUser().getId());
        blog.setUserId(blog.getUser().getId());
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTypeId(blog.getType().getId());
        blog.setTags(tagService.getTagsByString(blog.getTagIds()));
        int res = blogService.updateBlog(blog);
        if (res != 0){
            attributes.addFlashAttribute("message","恭喜，博客修改成功！");
        }else {
            attributes.addFlashAttribute("message","抱歉，博客信息修改失败！");
        }
        return "redirect:/admin/blogs";
    }

    //搜索博客
    @PostMapping("/blogs/search")
    public String search(SearchBlog searchBlog, Model model,
                         @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        System.out.println(searchBlog);
        List<BlogQuery> blogBySearch = blogService.getBlogByTitleOrTypeOrRecommend(searchBlog);
        PageHelper.startPage(pageNum, 6);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogBySearch);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/adminBlogs :: blogList";
    }
}
