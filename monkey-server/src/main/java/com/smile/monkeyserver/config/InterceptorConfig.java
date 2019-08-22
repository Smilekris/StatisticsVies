package com.smile.monkeyserver.config;

import com.smile.monkeyserver.listener.MonkeyHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 添加拦截器
 * @ClassName InterceptorConfig
 * @Author kris
 * @Date 2019/8/19
 **/
//@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private MonkeyHandlerAdapter monkeyHandlerAdapter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(monkeyHandlerAdapter);
    }
}
