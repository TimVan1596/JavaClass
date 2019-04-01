package com.antianbao.text.log4j;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.MDC;

public class Log4jFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        String ip = req.getRemoteAddr();
        //获取用户名
        String userName = (String) ((HttpServletRequest) req).getSession().getAttribute("userName");
        MDC.put("ip", ip);
        MDC.put("userName", userName);
        chain.doFilter(req, res);
        MDC.remove("ip");
        MDC.remove("userName");
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}