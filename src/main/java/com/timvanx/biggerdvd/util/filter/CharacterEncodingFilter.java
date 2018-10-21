package com.timvanx.biggerdvd.util.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 *  处理全站中文乱码问题
 * @author TimVan
 * @date 2018年10月22日01:25:02
 * 注解将一个实现了javax.servlet.Filter接口的类定义为过滤器
 */
@WebFilter(filterName="CharacterEncodingFilter",urlPatterns="/*")
public class CharacterEncodingFilter implements Filter {
    /**
     *  charset = 设置默认编码为 UTF-8
     */
    private String charset = "UTF-8";


    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //只能处理post方式的请求乱码
        request.setCharacterEncoding(charset);
        response.setCharacterEncoding(charset);
        response.setContentType("text/html;charset=" + charset);

        chain.doFilter(new CharacterEncodingHttpServletRequest(request), response);
    }

    /**
     *使用包装设计模式处理get方式的请求乱码
     */
    class CharacterEncodingHttpServletRequest extends HttpServletRequestWrapper {
        private HttpServletRequest request;

        public CharacterEncodingHttpServletRequest(HttpServletRequest request) {
            super(request);
            this.request = request;
        }

        @Override
        public String getParameter(String name) {
            String value = request.getParameter(name);
            //如果是非get方法,直接返回
            if (!"get".equalsIgnoreCase(request.getMethod())) {
                return value;
            }
            if (value == null) {
                return null;
            }
            try {
                return value = new String(value.getBytes("iso8859-1"), request.getCharacterEncoding());
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }

    }

}