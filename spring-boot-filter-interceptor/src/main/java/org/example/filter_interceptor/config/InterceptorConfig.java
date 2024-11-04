package org.example.filter_interceptor.config;

import lombok.AllArgsConstructor;
import org.example.filter_interceptor.filter.CorsFilter;
import org.example.filter_interceptor.interceptor.AuthInterceptor;
import org.example.filter_interceptor.interceptor.CORSInterceptor;
import org.example.filter_interceptor.interceptor.ImageUploadInterceptor;
import org.example.filter_interceptor.interceptor.LoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final LoggingInterceptor loggingInterceptor;

    private final AuthInterceptor authInterceptor;

    private final CORSInterceptor corsInterceptor;

    private final ImageUploadInterceptor imageUploadInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器并添加拦截地址
        registry.addInterceptor(loggingInterceptor).addPathPatterns("/**");
//        registry.addInterceptor(authInterceptor).addPathPatterns("/**");
//        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
        registry.addInterceptor(imageUploadInterceptor).addPathPatterns("/api/upload"); // 假设这是上传的路径



    }


}
