package org.example.filter_interceptor.filter;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class RateLimitFilter implements Filter {

    private static final int LIMIT = 5;
    private static final int TIME_WINDOW = 60 * 1000;
    //不同IP地址的请求
    private static final  ConcurrentHashMap<String,UserRequest> USER_REQUESTS = new java.util.concurrent.ConcurrentHashMap<>();
    //存储不同IP地址验证码
    private static final  ConcurrentHashMap<String,String> CAPTCHA_STORE = new java.util.concurrent.ConcurrentHashMap<>();



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取请求客户端地址
        String clientIp = request.getRemoteAddr();
        //计算该IP地址的请求信息，包括请求次数和时间
//        UserRequest userRequest = new UserRequest(1,System.currentTimeMillis());
//        USER_REQUEST.put(clientIp,userRequest);
        UserRequest userRequest = USER_REQUESTS.compute(clientIp,(key,value) -> {
           //如果该用户请求为空，或者超过时间窗口60秒，则重置计数
           if (value == null || System.currentTimeMillis() - value.timestamp > TIME_WINDOW) {
               return new UserRequest(1,System.currentTimeMillis());
           }else {
               //增加请求次数
               value.count++;
               //返回该用户请求对象
               return value;
           }
        });

        //检查用户请求是否超过限制，超过给出验证码
        if (userRequest.count > LIMIT){
            LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
            //获取验证码的文本内容
            String code = lineCaptcha.getCode();
            //存入该IP地址的验证码仓库
            CAPTCHA_STORE.put(clientIp,code);
            //把验证码返回客户端
            response.setContentType("image/png");

            ServletOutputStream outputStream = response.getOutputStream();
//            //把验证码图片通过字节输出流输出到客户端
//            lineCaptcha.write(response.getOutputStream());
            lineCaptcha.write(outputStream);
            outputStream.flush();
            outputStream.close();



        }

        filterChain.doFilter(request,response);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("RateLimitFilter init");
    }

    @Override
    public void destroy() {
       log.info("RateLimitFilter destroy");
    }

    private static class UserRequest {
        //计数
        int count;
        //最后一次请求时间戳
        long timestamp;

        UserRequest(int count , long timestamp){
            this.count = count;
            this.timestamp = timestamp;
        }
    }



}
