package com.smile.monkeyserver.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
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
    public final static String STATISTIS_QUEUE = "LINGTANGCUN_AFFARIR_ONE";


    /**
     * 定义string队列
     * @return
     */
    @Bean
    public Queue statistsQueue() {
        return new Queue(STATISTIS_QUEUE);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
}
