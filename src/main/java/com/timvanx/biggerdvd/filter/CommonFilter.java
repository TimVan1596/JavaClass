package com.timvanx.biggerdvd.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CommonFilter extends HttpFilter {

    private final Logger log = LoggerFactory.getLogger(CommonFilter.class);

    @Override
    public void doFilter(HttpServletRequest request,
                         HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        log.info("==============拦截get请求================");
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            RequestUtil.saveRequest(request);
        }
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());
        if ("/login".equals(url)) {
            filterChain.doFilter(request, response);
            return;
        } else {
            String username = (String) request.getSession().getAttribute("user");
            if (username == null) {
                log.info("被拦截：跳转到login页面！");
                request.getRequestDispatcher("/page/index1.jsp").forward(request, response);
            } else
                filterChain.doFilter(request, response);
        }
    }
}