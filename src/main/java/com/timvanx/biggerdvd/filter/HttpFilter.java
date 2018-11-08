package com.timvanx.biggerdvd.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HttpFilter
 */
public abstract class HttpFilter implements Filter{

    //保存filterConfig对象
    private FilterConfig filterConfig;

    /**
     * 直接返回filterConfig对象
     * @return
     */
    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    /**
     * 不建议子类直接覆盖，若直接失败，将可能导致filterConfig成员变量初始化失败
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        init();
    }

    /**
     * 供子类继承的初始化方法，刻通过getFilterConfig()方法获得filterConfig对象
     */
    private void init() {}

    /**
     * 原生的doFilter方法，在方法内部把ServletRequest和ServletResponse转化化为了HttpServletRequest和HttpServletResponse，
     * 并调用了doFilter(HttpServletRequest request, HttpServletResponse response,FilterChain filterChain)方法
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        doFilter(request, response, filterChain);
    }

    /**
     * 抽象方法，为http请求定制，必须实现的方法
     * @param request
     * @param response
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    public abstract void doFilter(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws IOException, ServletException;

    @Override
    public void destroy() {}

}