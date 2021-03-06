package com.imooc.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/5/24 10:12
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //使“localhost：8080/”，跳转到login/login.html
        registry.addViewController("/").setViewName("login/login");
    }
}
