package com.lot.blog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: blog
 * @description: 登录过滤器
 * @author: lotsehsin
 * @create: 2022-02-28 21:35
 **/
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        Object user = request.getSession().getAttribute("user");
        // 用户没有登陆跳转到登陆页面
       if (user == null) {
           response.sendRedirect("/admin");
           return false;

        }
        //如果有用户则放行
        return true;
    }
}
