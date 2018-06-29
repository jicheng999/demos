package com.example.demo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Created by JC on 2018/6/29.
 * 需要打成war包时要添加这个类，继成SpringBootServletInitializer，并覆盖方法configure
 * 如果要打成jar包则可不使用此类
 */
public class DemoServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder){
        //传入参数是有Main的入口函数,此处为DemoApplication类
        return builder.sources(DemoApplication.class);
    }
}
