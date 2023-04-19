package com.zhiqi.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author RyuJung
 * @since 2023/4/18-17:40
 */
@RestController
@Api("hello接口文档")
public class HelloController {

    @GetMapping("/hello")
    @ApiOperation("hello")
    public String hello(){
        return "Hello World";
    }

}
