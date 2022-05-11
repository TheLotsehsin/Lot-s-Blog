package com.lot.blog.controller;

import com.lot.blog.entity.Comment;
import com.lot.blog.entity.User;
import com.lot.blog.service.BlogService;
import com.lot.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: blog
 * @description: 评论操作
 * @author: lotsehsin
 * @create: 2022-03-28 23:45
 **/

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    /*查询评论*/
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        List<Comment> comments = commentService.getCommentByBlogId(blogId);
        model.addAttribute("comments",comments);
        return "blog :: commentList";
    }

    /*上传评论*/
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session,Model model){
        System.out.println("前端评论信息===="+comment);
        Long blogId = comment.getBlog().getId();
        Long parentCommentId = comment.getParentComment().getId();
        System.out.println("博客号=="+blogId+",父评论号==="+parentCommentId);
        User user = (User) session.getAttribute("user");
        System.out.println("管理员信息=="+user);
        comment.setBlogId(blogId);
        if (user != null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else{
            comment.setAvatar(avatar);
        }
        if (parentCommentId != null){
            comment.setParentCommentId(parentCommentId);
        }
        commentService.saveComment(comment);
        List<Comment> comments = commentService.getCommentByBlogId(blogId);
        model.addAttribute("comments",comments);
        return "redirect:/comments/"+blogId;
    }
}
