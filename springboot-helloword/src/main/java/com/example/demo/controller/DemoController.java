package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by JC on 2018/6/29.
 */
@RestController
public class DemoController {

    @RequestMapping("/")
    public String greeting() {
        return "Hello World!";
    }
}
