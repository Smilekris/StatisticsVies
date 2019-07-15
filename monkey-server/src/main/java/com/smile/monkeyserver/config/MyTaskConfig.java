package com.smile.monkeyserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @ClassName MyTaskConfig
 * @Author yamei
 * @Date 2019/7/15
 **/
@Configuration
@EnableScheduling
public class MyTaskConfig {

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        return new SchedulerFactoryBean();
    }

}
