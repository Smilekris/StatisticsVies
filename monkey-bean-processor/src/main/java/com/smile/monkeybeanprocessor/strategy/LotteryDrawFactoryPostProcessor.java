package com.smile.monkeybeanprocessor.strategy;

import cn.hutool.core.lang.ClassScanner;
import com.smile.monkeybeanprocessor.annotation.LotterDrawAnnotation;
import com.smile.monkeybeanprocessor.service.LotteryDraw;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName LotteryDrawFactoryPostProcessor
 * @Author kris
 * @Date 2020/8/17
 **/
@Component
@Slf4j
public class LotteryDrawFactoryPostProcessor implements BeanFactoryPostProcessor {
    public static final String PACKAGE_PATH ="com.smile.monkeybeanprocessor";

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<String, Class<? extends LotteryDraw>> lotteryDrawContext = new HashMap<>(2);
        ClassScanner.scanPackageByAnnotation(PACKAGE_PATH, LotterDrawAnnotation.class).forEach(clazz->{
            String typeName = clazz.getAnnotation(LotterDrawAnnotation.class).type();
            lotteryDrawContext.put(typeName, (Class<? extends LotteryDraw>) clazz);
        });
        LotteryDrawContext context = new LotteryDrawContext(lotteryDrawContext);
        beanFactory.registerSingleton(LotteryDrawContext.class.getName(),context);
    }
}
