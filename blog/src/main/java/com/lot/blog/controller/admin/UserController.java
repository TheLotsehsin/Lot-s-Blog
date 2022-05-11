package com.lot.blog.controller.admin;

import com.lot.blog.dao.UserDao;
import com.lot.blog.entity.User;
import com.lot.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


/**
 * @program: blog
 * @description: 用户功能控制
 * @author: lotsehsin
 * @create: 2022-02-27 23:03
 **/
@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String enterLogin(){
        return "admin/login";
    }

    @RequestMapping("/login")
    public String checkUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session,
                            RedirectAttributes attributes){
        User user = userService.checkUser(username, password);
        if (user!= null){
            user.setPassword(null);//设置密码为空，防止被session拿到密码
            session.setAttribute("user",user);
            System.out.println("======"+session.getAttribute("user"));
            return "admin/index";
        }else{
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";

    }
}
