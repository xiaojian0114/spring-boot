package org.example.filter_interceptor.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String authToken = request.getHeader("Authorization");
        if ("hello".equals(authToken)) {

            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("UNAUTHORIZED!!");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("AuthFilter init");
    }

    @Override
    public void destroy() {
        log.info("AuthFilter destroy");
    }
}
