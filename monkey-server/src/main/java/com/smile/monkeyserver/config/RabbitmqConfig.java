package com.smile.monkeyserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.*;

/**
 * @ClassName RabbitmqConfig
 * @Author kris
 * @Date 2019/7/8
 **/

@Configuration
public class RabbitmqConfig {
    /**
     * 定义队列名
     */
    private final static String STATISTIS_QUEUE = "LINGTANGCUN_AFFARIR_ONE";


    /**
     * 定义string队列
     * @return
     */
    @Bean
    public Queue string() {
        return new Queue(STATISTIS_QUEUE);
    }
}
