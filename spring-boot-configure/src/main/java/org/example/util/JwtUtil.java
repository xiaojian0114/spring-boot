package org.example.util;

import cn.hutool.jwt.JWTUtil;
import jakarta.annotation.Resource;
import org.example.config.JwtConfig;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
@Component
public class JwtUtil{

    @Resource
    private JwtConfig jwtConfig;

    public String generateToken(Map<String, Object> claims) {
        claims.put("exp" , new Date(System.currentTimeMillis() + jwtConfig.getExpiration()));
        return JWTUtil.createToken(claims , jwtConfig.getSecret().getBytes());
    }

    public boolean validateToken(String token) {
        return JWTUtil.verify(token, jwtConfig.getSecret().getBytes());
    }
}
