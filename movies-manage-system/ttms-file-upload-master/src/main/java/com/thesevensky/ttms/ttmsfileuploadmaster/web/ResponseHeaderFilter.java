package com.thesevensky.ttms.ttmsfileuploadmaster.web;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/24 15:16
 * @Version 1.0
 */
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseHeaderFilter implements Filter {
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
