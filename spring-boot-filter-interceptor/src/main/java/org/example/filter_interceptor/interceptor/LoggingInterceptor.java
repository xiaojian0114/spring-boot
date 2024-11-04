package org.example.filter_interceptor.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Component
@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String path = request.getRequestURI();
        String clientIp = request.getRemoteAddr();
        log.info("请求已经到达拦截器：path:{},clientIp:{},beginTime:{}",path,clientIp, LocalDateTime.now());
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("拦截器响应处理完毕：path:{},endTime:{}",request.getRequestURI(),LocalDateTime.now());
    }
}
