package com.timvanx.biggerdvd.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dhc on 17-5-18.
 * Description: 所有请求都走此过滤器来判断用户是否登录
 * user: 网络黑寡妇
 **/
@WebFilter(filterName="Servlet3Filter",urlPatterns="/ftm/html/*")
public class LoginFilter implements Filter{

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 获得在下面代码中要用的request,response,session对象
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        //1.获取请求URL
        String servletPath = httpRequest.getServletPath();

//        //2.检测1中获取的servletPath是否为不需要检测的URl中的一个.若是,放行
////        List<String> urls = Arrays.asList(uncheckedUrls.split(","));
////        if (urls.contains(servletPath)) {
////            filterChain.doFilter(httpRequest, httpResponse);
////            return;
////        }

        //3.从session中获取SessionKey对应值,若值不存在,则重定向到redirectUrl
        String redirectUrl = "/ftm/index.html";
        Object user = httpRequest.getSession().getAttribute("userEmail");
        if ((user == null)) {

            redirectUrl =  httpRequest.getContextPath() + redirectUrl;
            httpResponse.sendRedirect(redirectUrl);
            return;
        }

        //4.若存在,则放行
        filterChain.doFilter(httpRequest, httpResponse);
    }

}
