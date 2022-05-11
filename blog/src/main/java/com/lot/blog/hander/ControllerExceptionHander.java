package com.lot.blog.hander;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/*拦截所有有Controller注解的控制器*/
@ControllerAdvice
public class ControllerExceptionHander {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exception(HttpServletRequest request, Exception e)
    throws Exception{
        logger.error("Request URL:{}, Exception : {}",request.getRequestURL(),e);

       /*有状态码的异常直接交给springboot来处理*/
       if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("e",e);
        mv.setViewName("error/error"); //error
        return mv;
    }
}
