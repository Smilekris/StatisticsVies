package com.smile.monkeyserver.amqp;

import com.alibaba.fastjson.JSONObject;
import com.smile.monkeyserver.config.RabbitmqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName RabbitDirectProdicer
 * @Author yamei
 * @Date 2019/7/12
 **/
@Component
public class RabbitDirectProdicer {
    public final static Logger LOG = LoggerFactory.getLogger(RabbitDirectProdicer.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendTestA(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("test","ccc");
        this.rabbitTemplate.convertAndSend(RabbitmqConfig.TEST_DIRECT_QUEUE_A,jsonObject);
    }
    public void sendTestB(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("test","ccc");
        this.rabbitTemplate.convertAndSend(RabbitmqConfig.TEST_DIRECT_QUEUE_B,jsonObject);
    }
    public void sendTestC(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("test","ccc");
        this.rabbitTemplate.convertAndSend(RabbitmqConfig.TEST_DIRECT_QUEUE_C,jsonObject);
    }
}
