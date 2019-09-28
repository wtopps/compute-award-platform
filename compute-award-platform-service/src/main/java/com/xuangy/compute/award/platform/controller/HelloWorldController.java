package com.xuangy.compute.award.platform.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloWorldController
 * @Description
 * @Author xuangy
 * @Date 2019/9/28
 **/
@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    @GetMapping
    public String sayHelloWorld() {
        return "hello world!";
    }
}
