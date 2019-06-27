package com.thesevensky.ttms.ttmsproviderusermaster.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/10 14:09
 * @Version 1.0
 */
public class ResponseHeaderFilter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.addHeader("Access-Control-Allow-Methods", "*");
        httpServletResponse.addHeader("Access-Control-Allow-Headers","*");
        filterChain.doFilter(servletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {}
}