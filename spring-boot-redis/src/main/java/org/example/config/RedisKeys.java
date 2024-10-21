package org.example.config;

//统一维护redis中的key
public class RedisKeys {


    // 验证码 key
    public static String getSmsKey(String phone) {
        return "sms:captcha:" + phone;
    }

    public static String getAccessTokenKey(String accessToken) {
        return "sys:access:" + accessToken;
    }

    public static String getUserIdKey(Long id) {
        return "sys:userId:" + id;
    }
}
