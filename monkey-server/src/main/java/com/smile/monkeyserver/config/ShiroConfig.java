package com.smile.monkeyserver.config;

import com.smile.monkeyserver.config.oath2.MonkeyRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 引入权限控制-同时支持游客身份
 * @ClassName ShiroConfig
 * @Author kris
 * @Date 2019/12/11
 **/
@Configuration
public class ShiroConfig {


    @Bean("securityManager")
    public SecurityManager securityManager(MonkeyRealm monkeyRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(monkeyRealm);
        securityManager.setRememberMeManager(null);
        return securityManager;
    }
}
