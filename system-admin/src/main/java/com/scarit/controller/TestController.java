package com.scarit.controller;

import com.scarit.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {

    @Resource
    private RedisTemplate redisTemplate;

    @GetMapping("/test")
    public String test(){
        redisTemplate.setObject("map", List.of("Reese","Finch"),200L);
        return "hello ssm";
    }
}
