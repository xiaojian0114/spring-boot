package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.common.ResponseResult;
import org.example.mapper.SpecialMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/zhihu")

public class SpecialController {
    private final SpecialMapper specialMapper;

    @GetMapping("/specials")
    public ResponseResult getSpecials() {
        return ResponseResult.builder()
                .code(200)
                .msg("请求成功")
                .data(specialMapper.findAll())
                .build();
    }

}
