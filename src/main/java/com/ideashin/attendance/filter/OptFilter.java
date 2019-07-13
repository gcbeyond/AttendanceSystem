package com.ideashin.attendance.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: Shin
 * @Date: 2019/7/12 10:17
 * @Blog: ideashin.com
 */
public class OptFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException { ;
        HttpServletRequest req = (HttpServletRequest) request;
        String url = req.getRequestURL().toString();
        String[] urs = url.split("/");
        String opt = urs[ urs.length - 1 ];

        req.setAttribute("opt", opt);

        chain.doFilter(req, response);
    }

    @Override
    public void destroy() {

    }
}
