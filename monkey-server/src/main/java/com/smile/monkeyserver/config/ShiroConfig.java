package com.smile.monkeyserver.config;

import com.smile.monkeyserver.config.oath2.MonkeyFilter;
import com.smile.monkeyserver.config.oath2.MonkeyRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        //oauth过滤
        Map<String, Filter> filters = new HashMap<>();
        filters.put("monkey", new MonkeyFilter());
        shiroFilter.setFilters(filters);

        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/statistics/app/**", "anon");
        filterMap.put("/statistics/**", "anon");
        filterMap.put("/api/login", "anon");
        filterMap.put("/api/**", "anon");
        filterMap.put("/big/menu", "monkey");
        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }
}
