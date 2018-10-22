package com.timvanx.biggerdvd.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


/**
 *  处理全站中文乱码问题
 * @author TimVan
 * @date 2018年10月22日01:25:02
 * 注解将一个实现了javax.servlet.Filter接口的类定义为过滤器
 */
//@WebFilter(filterName="CharacterEncodingFilter",urlPatterns="/*")
public class CharacterEncodingFilter implements Filter {
    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        // 解决POST请求参数乱码问题
        request.setCharacterEncoding("UTF-8");

        req = new MyRequest(req);

        chain.doFilter(req, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }



}