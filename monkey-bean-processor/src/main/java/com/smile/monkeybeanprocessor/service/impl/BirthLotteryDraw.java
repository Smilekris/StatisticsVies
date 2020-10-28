package com.smile.monkeybeanprocessor.service.impl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName BirthLotteryDraw
 * @Author kris
 * @Date 2020/8/21
 **/
public class BirthLotteryDraw implements InitializingBean, BeanNameAware, BeanFactoryAware {
    private ConfigurableListableBeanFactory beanFactory;
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("beanFactoryAware face"+this.beanFactory);
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("App.setBeanName():" + name);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.beanFactory.registerSingleton(BirthLotteryDraw.class.getName(),BirthLotteryDraw.class);
    }
}
