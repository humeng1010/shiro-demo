package com.demo.controller;

import com.demo.common.Result;
import com.demo.entity.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public Result resource(){
        Resource resource = new Resource(1L, Arrays.asList("用户管理", "商品管理", "订单管理", "物流管理"));

        return Result.success(resource);
    }
}
