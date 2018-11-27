package com.antianbao.book;

import javax.servlet.Filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "FilterSession",urlPatterns = {"/atb/book/display.jsp"})
public class FilterSession implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest hrequest=(HttpServletRequest)req;
        HttpServletResponse hresponse=(HttpServletResponse)resp;

        Object user=hrequest.getSession().getAttribute("loginName");
        if(user!=null) {
            chain.doFilter(req, resp);
        }else{
            hresponse.sendRedirect("/atb/book/Login.jsp");
       }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
