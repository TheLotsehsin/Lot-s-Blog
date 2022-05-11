package com.lot.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: blog
 * @description: 关于我
 * @author: lotsehsin
 * @create: 2022-03-28 23:44
 **/
@Controller
public class AboutMeController {

    @GetMapping("/aboutMe")
    public String aboutMe(){
        return "aboutMe";
    }
}
