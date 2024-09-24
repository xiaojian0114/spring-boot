package org.example.controller;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.example.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/jwt")
@AllArgsConstructor
public class JwtController {
    @Resource
    private JwtUtil jwtUtil;

    @PostMapping("/generate")
    public Map<String ,String> generateToken(@RequestParam String username){
        Map<String, Object> claims = new HashMap<>();
        String token = jwtUtil.generateToken(claims);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }

    @PostMapping("/validate")
    public Map<String ,Object> validateToken(@RequestParam String token){
        boolean isValid = jwtUtil.validateToken(token);
        Map<String, Object> response = new HashMap<>();
        response.put("isValid", isValid);
        return response;
    }


}
