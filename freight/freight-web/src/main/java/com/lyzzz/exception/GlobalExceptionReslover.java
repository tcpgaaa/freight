package com.lyzzz.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GlobalExceptionReslover implements HandlerExceptionResolver {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionReslover.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.error("系统发生异常",ex);
        //发邮件、发短信
        //Jmail：可以查找相关的资料
        //需要在购买短信。调用第三方接口即可。

        //跳转的错误页面和要显示的异常信息
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message","系统发生异常,请联系管理员");
        modelAndView.addObject("stack",ex);
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
