package com.smile.monkeyserver.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    public final static String TEST_BLOCK_QUEUE = "TEST_CJW";
    public final static String TEST_DIRECT_QUEUE_A = "TEST_DIRECT_A";
    public final static String TEST_DIRECT_QUEUE_B = "TEST_DIRECT_B";
    public final static String TEST_DIRECT_QUEUE_C = "TEST_DIRECT_C";
    public final static String ACTIVE_NUM_QUEUE = "ACTIVE_NUM";


    /**
     * 定义string队列
     * @return
     */
    @Bean
    public Queue statistsQueue() {
        return new Queue(STATISTIS_QUEUE);
    }

    @Bean
    public Queue testQueue() {
        return new Queue(TEST_BLOCK_QUEUE);
    }

    @Bean
    public Queue activeNumQueue() {
        return new Queue(ACTIVE_NUM_QUEUE);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    // ===============以下是验证Direct Exchange的队列和交互机==========
    @Bean
    public Queue directQueueA() {
        return new Queue(TEST_DIRECT_QUEUE_A);
    }

    @Bean
    public Queue directQueueB() {
        return new Queue(TEST_DIRECT_QUEUE_B);
    }

    @Bean
    public Queue directQueueC() {
        return new Queue(TEST_DIRECT_QUEUE_C);
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    @Bean
    Binding bindingDirectExchangeA(Queue directQueueA, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueueA).to(directExchange).with("direct.a");
    }

    @Bean
    Binding bindingDirectExchangeB(Queue directQueueB, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueueB).to(directExchange).with("direct.a");
    }

    @Bean
    Binding bindingDirectExchangeC(Queue directQueueC, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueueC).to(directExchange).with("direct.a");
    }

}
