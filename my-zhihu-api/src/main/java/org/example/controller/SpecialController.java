package org.example.controller;

import jakarta.annotation.Resource;
import org.example.common.ResponseResult;
import org.example.enity.Special;
import org.example.service.SpecialService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.ibatis.ognl.DynamicSubscript.all;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/special")
public class SpecialController {
    @Resource
    private SpecialService specialService;

    @GetMapping("all")
    public ResponseResult getAll() {
        List<Special> all = specialService.getAll();
        return ResponseResult.builder()
                .code(200)
                .msg("数据获取成功")
                .data(all)
                .build();
    }
    @GetMapping("/page")
    public ResponseResult getByPage(@RequestParam int limit,@RequestParam int offset) {
        Map<String,Object> map = new HashMap<>();
        List<Special> specials = specialService.getByPage(limit, offset);
        map.put("specials",specials);
        map.put("total",specialService.getAll().size());
        return ResponseResult.builder()
                .code(200)
                .msg("数据获取成功")
                .data(map)
                .build();
    }
}
