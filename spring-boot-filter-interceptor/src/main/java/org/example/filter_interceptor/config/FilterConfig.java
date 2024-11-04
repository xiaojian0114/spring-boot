package org.example.filter_interceptor.config;

import org.example.filter_interceptor.filter.*;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

//    @Bean
//    public FilterRegistrationBean<CustomFilter> filterRegistrationBean() {
//        FilterRegistrationBean<CustomFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new CustomFilter());
//        //设置过滤器拦截的url路径
//        registrationBean.addUrlPatterns("/*");
//        //设置过滤器的执行顺序
//        // 数字越小 优先级越高
//        registrationBean.setOrder(1);
//        return registrationBean;
//
//    }

//    @Bean
//    public FilterRegistrationBean<LoggingFilter> loggingFilter() {
//        FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new LoggingFilter());
//        //设置过滤器拦截的url路径
//        registrationBean.addUrlPatterns("/*");
//        //设置过滤器的执行顺序
//        // 数字越小 优先级越高
//        registrationBean.setOrder(1);
//        return registrationBean;
//
//    }


//    @Bean
//    public FilterRegistrationBean<AuthFilter> authFilter() {
//        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new AuthFilter());
//        //设置过滤器拦截的url路径
//        registrationBean.addUrlPatterns("/*");
//        //设置过滤器的执行顺序
//        // 数字越小 优先级越高
//        registrationBean.setOrder(1);
//        return registrationBean;
//
//    }
//
//    @Bean
//    public FilterRegistrationBean<CorsFilter> corsFilter() {
//        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new CorsFilter());
//        //设置过滤器拦截的url路径
//        registrationBean.addUrlPatterns("/*");
//        //设置过滤器的执行顺序
//        // 数字越小 优先级越高
//        registrationBean.setOrder(1);
//        return registrationBean;
//
//    }

    @Bean
    public FilterRegistrationBean<RateLimitFilter> rateLimitFilter() {
        FilterRegistrationBean<RateLimitFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RateLimitFilter());
        //设置过滤器拦截的url路径
        registrationBean.addUrlPatterns("/*");
        //设置过滤器的执行顺序
        // 数字越小 优先级越高
        registrationBean.setOrder(1);
        return registrationBean;

    }

}
