package org.example.filter_interceptor.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //允许所有的来源
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,OPTIONS");
        response.setHeader("Access-Control-Allow-Origin", "Content-Type , Authorization");

        filterChain.doFilter(servletRequest, servletResponse);



    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("CoresFilter init");
    }

    @Override
    public void destroy() {
       log.info("CoresFilter destroy");
    }
}
